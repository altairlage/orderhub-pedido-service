package com.fiap.orderhub.orderhub_pedido_service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueEntity, String> {

    Optional<EstoqueEntity> findById(String id);
}
