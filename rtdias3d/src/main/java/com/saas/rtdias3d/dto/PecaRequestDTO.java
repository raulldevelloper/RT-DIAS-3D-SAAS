package com.saas.rtdias3d.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PecaRequestDTO(
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotNull(message = "Peso é obrigatório") @Positive(message = "Peso deve ser maior que zero") BigDecimal peso,
        @NotNull(message = "Altura é obrigatória") @Positive(message = "Altura deve ser maior que zero") BigDecimal altura,
        @NotNull(message = "Largura é obrigatória") @Positive(message = "Largura deve ser maior que zero") BigDecimal largura,
        @NotNull(message = "Comprimento é obrigatório") @Positive(message = "Comprimento deve ser maior que zero") BigDecimal comprimento,
        @NotNull(message = "Usuário é obrigatório") @Positive(message = "Usuário inválido") Integer usuarioId
) {}
