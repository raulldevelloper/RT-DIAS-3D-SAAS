package com.saas.rtdias3d.dto;

import com.saas.rtdias3d.entitys.TipoFilamento;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.math.BigDecimal;

public record FilamentoRequestDTO(
        @NotNull(message = "Tipo de filamento é obrigatório") TipoFilamento tipo,
        @NotNull(message = "Gramas compradas é obrigatório") @Positive(message = "Gramas deve ser maior que zero") BigDecimal gramasCompradas,
        @NotNull(message = "Preço total é obrigatório") @Positive(message = "Preço deve ser maior que zero") BigDecimal precoTotalCompra,
        @NotNull(message = "Data da compra é obrigatória") LocalDate dataCompra
) {}
