package com.saas.rtdias3d.dto;

import java.math.BigDecimal;

public record PecaResponseDTO(
        int id,
        String nome,
        BigDecimal peso,
        BigDecimal altura,
        BigDecimal largura,
        BigDecimal comprimento

) {
}
