package com.saas.rtdias3d.repositories;

import com.saas.rtdias3d.entitys.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<VendaEntity, Integer> {

    List<VendaEntity> findByUsuarioId(int usuarioId);

    List<VendaEntity> findByClienteId(int clienteId);

    List<VendaEntity> findByPecaId(int pecaId);
}
