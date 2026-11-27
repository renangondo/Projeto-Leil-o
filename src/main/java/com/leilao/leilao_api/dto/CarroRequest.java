package com.leilao.leilao_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CarroRequest (

    @NotBlank(message = "A marca é obrigatória")
    String marca,

    @NotBlank(message = "O modelo é obrigatório")
    String modelo,

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1900, message = "Ano inválido")
    Integer ano,

    @Size(max = 500, message = "A URL da foto é muito grande")
    String fotoUrl,

    @Size(max = 1000, message = "A descrição pode ter no máximo 1000 caracteres")
    String descricao,

    @NotNull(message = "O lance inicial é obrigatório")
    @Positive(message = "O lance inicial deve ser maior que zero")
    BigDecimal lanceInicial,

    @NotNull(message = "A data de encerramento é obrigatória")
    @Future(message = "A data de encerramento deve ser no futuro")
    LocalDateTime dataEncerramento    

){}
