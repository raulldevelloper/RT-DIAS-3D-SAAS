package com.saas.rtdias3d.dto;

import com.saas.rtdias3d.entitys.TipoFilamento;

import java.math.BigDecimal;

public record OrcamentoResponseDTO(
        int id,
        String nomePeca,
        TipoFilamento tipoFilamento,
        BigDecimal quantidadeGramas,
        int tempoImpressaoMinutos,
        BigDecimal precoFilamento100g,
        BigDecimal custoMaterial,
        BigDecimal custoEnergia,
        BigDecimal margemLucro,
        BigDecimal valorTotal
) {}
