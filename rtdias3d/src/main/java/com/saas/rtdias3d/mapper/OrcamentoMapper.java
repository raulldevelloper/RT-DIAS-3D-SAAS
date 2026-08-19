package com.saas.rtdias3d.mapper;

import com.saas.rtdias3d.dto.OrcamentoResponseDTO;
import com.saas.rtdias3d.entitys.OrcamentoEntity;

public class OrcamentoMapper {
    public static OrcamentoResponseDTO toResponseDTO(OrcamentoEntity entity) {
        return new OrcamentoResponseDTO(
                entity.getId(),
                entity.getPeca().getNome(),
                entity.getTipoFilamento(),
                entity.getQuantidadeGramas(),
                entity.getTempoImpressaoMinutos(),
                entity.getCustoEnergia(),
                entity.getPrecoFilamento100g(),
                entity.getMargemLucro(),
                entity.getValorTotal(),
                entity.getValorTotal());
    }
}
