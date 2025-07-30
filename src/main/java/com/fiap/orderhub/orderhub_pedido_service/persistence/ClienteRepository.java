package com.fiap.orderhub.orderhub_pedido_service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
    Optional<ClienteEntity> findById(String id);
}