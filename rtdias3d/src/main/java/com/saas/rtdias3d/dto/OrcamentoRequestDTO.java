package com.saas.rtdias3d.dto;

import com.saas.rtdias3d.entitys.TipoFilamento;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record OrcamentoRequestDTO(
        @NotNull(message = "Peça é obrigatória") @Positive(message = "Peça inválida") Integer pecaId,
        @NotNull(message = "Usuário é obrigatório") @Positive(message = "Usuário inválido") Integer usuarioId,
        @NotNull(message = "Tipo de filamento é obrigatório") TipoFilamento tipoFilamento,
        @NotNull(message = "Quantidade de gramas é obrigatória") @Positive(message = "Quantidade deve ser maior que zero") BigDecimal quantidadeGramas,
        @NotNull(message = "Tempo de impressão é obrigatório") @Positive(message = "Tempo deve ser maior que zero") Integer tempoImpressaoMinutos,
        @NotNull(message = "Custo de energia é obrigatório") @PositiveOrZero(message = "Custo não pode ser negativo") BigDecimal custoEnergia
) {}
