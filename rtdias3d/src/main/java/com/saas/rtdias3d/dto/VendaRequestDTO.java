package com.saas.rtdias3d.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record VendaRequestDTO(
        Integer orcamentoId, // opcional
        Integer pecaId,      // obrigatório só se orcamentoId for null (validado no service)
        @NotNull(message = "Usuário é obrigatório") @Positive(message = "Usuário inválido") Integer usuarioId,
        @NotNull(message = "Cliente é obrigatório") @Positive(message = "Cliente inválido") Integer clienteId,
        @NotNull(message = "Quantidade é obrigatória") @Positive(message = "Quantidade deve ser maior que zero") Integer quantidade,
        @NotNull(message = "Valor unitário é obrigatório") @Positive(message = "Valor deve ser maior que zero") BigDecimal valorUnitario
) {}