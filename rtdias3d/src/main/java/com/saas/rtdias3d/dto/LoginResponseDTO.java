package com.saas.rtdias3d.dto;

public record LoginResponseDTO(
        String token,
        String nome,
        int usuarioId
) {}
