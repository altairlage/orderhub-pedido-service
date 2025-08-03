package com.fiap.orderhub.orderhub_pedido_service.usecases;

import br.com.orderhub.core.domain.entities.Pedido;
import br.com.orderhub.core.dto.pedidos.PedidoDTO;
import br.com.orderhub.core.interfaces.IPedidoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarStatusPedidoUseCase {
    @Autowired
    private IPedidoGateway pedidoGateway;

    public void executar(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoGateway.buscarPorId(pedidoDTO.idPedido());
        if (pedido == null) {
            throw new RuntimeException("Pedido não encontrado");
        }
        pedido.setStatus(pedidoDTO.status());
        pedidoGateway.editar(pedido, pedido); //corrigir o atualizar
    }
}