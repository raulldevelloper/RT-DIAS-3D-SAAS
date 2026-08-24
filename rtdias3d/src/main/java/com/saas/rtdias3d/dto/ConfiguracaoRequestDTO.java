package com.saas.rtdias3d.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ConfiguracaoRequestDTO(
        @NotNull(message = "Valor da assinatura é obrigatório")
        @Positive(message = "Valor deve ser maior que zero")
        BigDecimal valorAssinatura,

        @NotBlank(message = "WhatsApp é obrigatório")
        String whatsapp
) {}
