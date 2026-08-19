package com.saas.rtdias3d.dto;

import com.saas.rtdias3d.entitys.TipoFilamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FilamentoResponseDTO(
        int id,
        TipoFilamento tipo,
        BigDecimal gramasCompradas,
        BigDecimal precoTotalCompra,
        LocalDate dataCompra
) {
}
