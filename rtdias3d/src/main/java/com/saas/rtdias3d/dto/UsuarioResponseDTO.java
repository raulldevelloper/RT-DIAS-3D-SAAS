package com.saas.rtdias3d.dto;

import java.math.BigDecimal;

public record UsuarioResponseDTO(
        int id,
        String nome,
        String email,
        BigDecimal margemLucroPadrao
) {
}
