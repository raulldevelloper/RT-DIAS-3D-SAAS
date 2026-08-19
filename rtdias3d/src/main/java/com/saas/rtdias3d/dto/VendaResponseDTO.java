package com.saas.rtdias3d.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VendaResponseDTO(
        int id,
        String nomePeca,
        String nomeCliente,
        int quantidade,
        BigDecimal valorUnitario,
        BigDecimal valorTotal,
        LocalDateTime dataVenda
) {}
