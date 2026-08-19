package com.saas.rtdias3d.repositories;

import com.saas.rtdias3d.entitys.FilamentoEntity;
import com.saas.rtdias3d.entitys.TipoFilamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilamentoRepository extends JpaRepository<FilamentoEntity, Integer> {
    List<FilamentoEntity> findByTipo(TipoFilamento tipo);
}
