package com.leilao.leilao_api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.leilao.leilao_api.dto.LanceRequest;
import com.leilao.leilao_api.dto.LanceResponse;
import com.leilao.leilao_api.entity.Carro;
import com.leilao.leilao_api.entity.Lance;
import com.leilao.leilao_api.entity.Usuario;
import com.leilao.leilao_api.repository.LanceRepository;
import com.leilao.leilao_api.repository.UsuarioRepository;

@Service
public class LanceService {

    private final LanceRepository lanceRepository;
    private final UsuarioRepository usuarioRepository;
    private final CarroService carroService;

    public LanceService(LanceRepository lanceRepository,
                        UsuarioRepository usuarioRepository,
                        CarroService carroService) {
        this.lanceRepository = lanceRepository;
        this.usuarioRepository = usuarioRepository;
        this.carroService = carroService;
    }

    public List<LanceResponse> listarPorCarro(Long carroId) {
        carroService.buscarEntidade(carroId); // garante erro 404 se o carro não existir

        return lanceRepository.findByCarroIdOrderByValorDesc(carroId)
                .stream()
                .map(LanceResponse::from)
                .toList();
    }

    @Transactional
    public LanceResponse darLance(Long carroId, LanceRequest dados) {
        // Regra 1: o carro precisa existir
        Carro carro = carroService.buscarEntidade(carroId);

        // Regra 2: o leilão não pode estar encerrado
        if (carro.estaEncerrado()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este leilão já foi encerrado");
        }

        Usuario usuario = usuarioRepository.findById(dados.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Optional<Lance> maiorLance = lanceRepository.findFistByCarroIdOrderByValorDesc(carroId);

        if (maiorLance.isEmpty()) {
            // Regra 3: primeiro lance precisa ser >= lance inicial
            if (dados.valor().compareTo(carro.getLanceInicial()) < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "O primeiro lance deve ser de pelo menos R$ " + carro.getLanceInicial());
            }
        } else {
            Lance atual = maiorLance.get();

            // Regra 5: não pode cobrir o próprio lance
            if (atual.getUsuario().getId().equals(usuario.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você já tem o maior lance deste carro");
            }

            // Regra 4: precisa ser maior que o maior lance atual
            if (dados.valor().compareTo(atual.getValor()) <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "O lance deve ser maior que R$ " + atual.getValor());
            }
        }

        Lance lance = new Lance();
        lance.setValor(dados.valor());
        lance.setDataHora(LocalDateTime.now());
        lance.setCarro(carro);
        lance.setUsuario(usuario);

        return LanceResponse.from(lanceRepository.save(lance));
    }
}