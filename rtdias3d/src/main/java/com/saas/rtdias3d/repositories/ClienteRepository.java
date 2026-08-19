package com.saas.rtdias3d.repositories;

import com.saas.rtdias3d.entitys.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    List<ClienteEntity> findByUsuarioId(int usuarioId);
}
