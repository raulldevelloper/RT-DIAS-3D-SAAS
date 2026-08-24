package com.saas.rtdias3d.dto;

import java.math.BigDecimal;

public record ConfiguracaoResponseDTO(
        BigDecimal valorAssinatura,
        String whatsapp
) {}
