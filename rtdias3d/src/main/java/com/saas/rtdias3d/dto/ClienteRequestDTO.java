package com.saas.rtdias3d.dto;

import jakarta.validation.constraints.*;

public record ClienteRequestDTO(
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "Telefone é obrigatório") String telefone,
        @NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email,
        @NotNull(message = "Usuário é obrigatório") @Positive(message = "Usuário inválido") Integer usuarioId
) {}