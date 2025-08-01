package com.fiap.orderhub.orderhub_pedido_service.persistence;

import br.com.orderhub.core.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Optional<Cliente> findById(Long id);
}