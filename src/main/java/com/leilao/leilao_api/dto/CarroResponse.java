package com.leilao.leilao_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record  CarroResponse (

    Long id,
    String marca,
    String modelo,
    Integer ano,
    String fotoUrl, 
    String descricao,
    BigDecimal lanceInicial,
    LocalDateTime dataEncerramento,
    BigDecimal maiorLance, // null se ainda não ter lances
    String usuarioMaiorLance, // nome de quem está ganhando
    long totalLances,
    boolean encerrado
){}
