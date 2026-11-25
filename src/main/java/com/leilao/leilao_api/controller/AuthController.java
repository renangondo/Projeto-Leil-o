package com.leilao.leilao_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leilao.leilao_api.service.UsuarioService;

@RestController 
@RequestMapping("api/auth")
public class AuthController {
    
    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this usuarioService = usuarioService
    }
}
