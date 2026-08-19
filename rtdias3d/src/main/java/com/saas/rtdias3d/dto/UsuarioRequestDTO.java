package com.saas.rtdias3d.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record UsuarioRequestDTO(
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email,
        @NotBlank(message = "Senha é obrigatória") @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres") String senha,
        @NotNull(message = "Margem de lucro é obrigatória") @PositiveOrZero(message = "Margem não pode ser negativa") BigDecimal margemLucroPadrao
) {}
