package com.fiap.orderhub.orderhub_pedido_service.adapters;

import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import com.fiap.orderhub.orderhub_pedido_service.service.OrquestradorCriacaoPedido;
import com.fiap.orderhub.orderhub_pedido_service.usecases.ProcessarPedidosUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class PedidoQueueListener {
    private final OrquestradorCriacaoPedido criacaoPedido;

    public PedidoQueueListener(OrquestradorCriacaoPedido criacaoPedido) {
        this.criacaoPedido = criacaoPedido;
    }

    @RabbitListener(queues = "orderhub-pedido-queue")

    public void consumir(CriarPedidoDTO dto) {
        criacaoPedido.criarPedido(dto);
    }
}