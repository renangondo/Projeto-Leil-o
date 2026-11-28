package com.leilao.leilao_api.service;

import com.leilao.leilao_api.dto.CarroRequest;
import com.leilao.leilao_api.dto.CarroResponse;
import com.leilao.leilao_api.entity.Carro;
import com.leilao.leilao_api.entity.Lance;
import com.leilao.leilao_api.repository.CarroRepository;
import com.leilao.leilao_api.repository.LanceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    private final CarroRepository carroRepository;
    private final LanceRepository lanceRepository;

    public CarroService(CarroRepository carroRepository, LanceRepository lanceRepository) {
        this.carroRepository = carroRepository;
        this.lanceRepository = lanceRepository;
    }

    public List<CarroResponse> listar() {
        return carroRepository.findAll()
                .stream()
                .map(this::paraResposta)
                .toList();
    }

    public CarroResponse buscarPorId(Long id) {
        return paraResposta(buscarEntidade(id));
    }

    public CarroResponse cadastrar(CarroRequest dados) {
        Carro carro = new Carro();
        carro.setMarca(dados.marca().trim());
        carro.setModelo(dados.modelo().trim());
        carro.setAno(dados.ano());
        carro.setFotoUrl(dados.fotoUrl());
        carro.setDescricao(dados.descricao());
        carro.setLanceInicial(dados.lanceInicial());
        carro.setDataEncerramento(dados.dataEncerramento());

        return paraResposta(carroRepository.save(carro));
    }

    // Usado aqui e no LanceService: busca o carro ou devolve erro 404
    public Carro buscarEntidade(Long id) {
        return carroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado"));
    }

    // Converte a entidade Carro na resposta, calculando os dados do leilão
    private CarroResponse paraResposta(Carro carro) {
        Optional<Lance> maiorLance = lanceRepository.findFistByCarroIdOrderByValorDesc(carro.getId());
        long totalLances = lanceRepository.countByCarroId(carro.getId());

        return new CarroResponse(
                carro.getId(),
                carro.getMarca(),
                carro.getModelo(),
                carro.getAno(),
                carro.getFotoUrl(),
                carro.getDescricao(),
                carro.getLanceInicial(),
                carro.getDataEncerramento(),
                maiorLance.map(Lance::getValor).orElse(null),
                maiorLance.map(lance -> lance.getUsuario().getNome()).orElse(null),
                totalLances,
                carro.estaEncerrado()
        );
    }
}