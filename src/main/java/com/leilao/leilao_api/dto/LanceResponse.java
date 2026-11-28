package com.leilao.leilao_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.leilao.leilao_api.entity.Lance;

public record LanceResponse (Long id, BigDecimal valor, LocalDateTime dataHora, String nomeUsuario ){

    public static LanceResponse from (Lance lance) {
        return new LanceResponse(
            lance.getId(),
            lance.getValor(),
            lance.getDataHora(),
            lance.getUsuario().getNome()
        );
    }
}
