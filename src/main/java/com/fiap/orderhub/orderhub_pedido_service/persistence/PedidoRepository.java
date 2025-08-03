package com.fiap.orderhub.orderhub_pedido_service.persistence;

import br.com.orderhub.core.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {

}
