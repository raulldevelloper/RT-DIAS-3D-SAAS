package com.saas.rtdias3d.repositories;

import com.saas.rtdias3d.entitys.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    long countByUltimoLoginIsNotNull();
    long countByUltimoLoginAfter(LocalDateTime data);
}
