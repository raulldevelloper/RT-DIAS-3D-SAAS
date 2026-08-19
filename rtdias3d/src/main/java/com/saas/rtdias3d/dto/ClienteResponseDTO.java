package com.saas.rtdias3d.dto;

public record ClienteResponseDTO(
        int id,
        String nome,
        String telefone,
        String email
) {}
