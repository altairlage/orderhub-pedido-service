package com.fiap.orderhub.orderhub_pedido_service.gateway.impl;

import br.com.orderhub.core.domain.entities.Pedido;
import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.interfaces.IPedidoGateway;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PedidoGatewayImpl implements IPedidoGateway {

    private final PedidoRepository pedidoRepository;

    @Override
    public Pedido buscarPorId(Long idPedido) {
        return null;
    }

    @Override
    public List<Pedido> buscarPorIdCliente(Long idCliente) {
        return List.of();
    }

    @Override
    public Pedido criar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido editar(Pedido pedidoAntigo, Pedido pedidoAtualizado) {
        return null;
    }

    @Override
    public Pedido editarStatus(Long idPedido, StatusPedido status) {
        return null;
    }

    @Override
    public List<Pedido> listarTodos() {
        return List.of();
    }
}
