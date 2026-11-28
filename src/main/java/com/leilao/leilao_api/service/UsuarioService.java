package com.leilao.leilao_api.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.leilao.leilao_api.dto.CadastroRequest;
import com.leilao.leilao_api.dto.LoginRequest;
import com.leilao.leilao_api.dto.UsuarioResponse;
import com.leilao.leilao_api.entity.Usuario;
import com.leilao.leilao_api.repository.UsuarioRepository;

@Service 
public class UsuarioService {
    
    private final  UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse cadastrar(CadastroRequest dados) {
        String email = dados.email().trim().toLowerCase();

       if (usuarioRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este e-mail já está cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setName(dados.nome().trim());
        usuario.setEmail(email);
        usuario.setSenha(encoder.encode(dados.senha()));

        Usuario salvo = usuarioRepository.save(usuario);

        return UsuarioResponse.from(salvo);
    }

    public UsuarioResponse login (LoginRequest dados) {
        String email = dados.email().trim().toLowerCase();

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos"));

        if (!encoder.matches(dados.senha(), usuario.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos");
        }

        return UsuarioResponse.from(usuario);

    }
}
