package com.fiap.orderhub.orderhub_pedido_service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, String> {
    List<PedidoEntity> findByIdCliente(Long idCliente);

}
