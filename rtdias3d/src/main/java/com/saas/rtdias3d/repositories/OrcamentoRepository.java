package com.saas.rtdias3d.repositories;

import com.saas.rtdias3d.entitys.OrcamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrcamentoRepository extends JpaRepository<OrcamentoEntity, Integer> {

    List<OrcamentoEntity> findByUsuarioId(int usuarioId);

    List<OrcamentoEntity> findByPecaId(int pecaId);
}
