package com.leilao.leilao_api.dto;

import com.leilao.leilao_api.entity.Usuario;

public record UsuarioResponse(Long id, String nome, String email) {
    
    public static UsuarioResponse from(Usuario usuario) {

        return new UsuarioResponse(usuario.getId(), usuario.getName(), usuario.getEmail());
    }
}
