package com.leilao.leilao_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record  LanceRequest (

    @NotNull(message = "O usuários é obrigatório")
    Long usuarioId,

    @NotNull(message = "O valor do lance é obrigário")
    @Positive(message = "O valor do lance deve ser maior que zero")
    BigDecimal valor
){}


