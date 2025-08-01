package com.fiap.orderhub.orderhub_pedido_service.persistence;

import br.com.orderhub.core.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findById(Long id);
}
