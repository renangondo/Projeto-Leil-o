package com.leilao.leilao_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leilao.leilao_api.dto.CadastroRequest;
import com.leilao.leilao_api.dto.LoginRequest;
import com.leilao.leilao_api.dto.UsuarioResponse;
import com.leilao.leilao_api.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse cadastrar(@RequestBody @Valid CadastroRequest dados) {
        return usuarioService.cadastrar(dados);
    }

    @PostMapping("/login")
    public UsuarioResponse login(@RequestBody @Valid LoginRequest dados) {
        return usuarioService.login(dados);
    }
}