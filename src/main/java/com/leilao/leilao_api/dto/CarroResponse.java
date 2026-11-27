package com.leilao.leilao_api.dto;

import java.math.BigDecimal;

public record  CarroResponse (

    Long id,
    String marca,
    String modelo,
    Integer ano,
    String fotoUrl, 
    String descricao,
    BigDecimal maiorLance, // null se ainda não ter lances
    String usuarioMaiorLance, // nome de quem está ganhando
    long totalLances,
    boolean encerrado
){}
