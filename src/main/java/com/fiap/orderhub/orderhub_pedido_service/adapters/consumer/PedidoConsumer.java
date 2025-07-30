package com.fiap.orderhub.orderhub_pedido_service.adapters.consumer;

import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import com.fiap.orderhub.orderhub_pedido_service.usecases.ProcessarPedidosUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

    private final ProcessarPedidosUseCase processarPedidoUseCase;

    public PedidoConsumer(ProcessarPedidosUseCase processarPedidoUseCase) {
        this.processarPedidoUseCase = processarPedidoUseCase;
    }

    @RabbitListener(queues = "pedido-queue")
    public void receiveMessage(CriarPedidoDTO pedido) {
        processarPedidoUseCase.run(pedido);
    }
}
