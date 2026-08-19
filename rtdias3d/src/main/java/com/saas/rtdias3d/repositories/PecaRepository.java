package com.saas.rtdias3d.repositories;

import com.saas.rtdias3d.entitys.PecaEntity;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PecaRepository extends JpaRepository<PecaEntity, Integer> {
    List<PecaEntity> findByUsuarioId(int usuarioId);
}
