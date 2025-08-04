package com.fiap.orderhub.orderhub_pedido_service.adapters;

import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import com.fiap.orderhub.orderhub_pedido_service.usecases.ProcessarPedidosUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class PedidoQueueListener {

    private final ProcessarPedidosUseCase useCase;

    public PedidoQueueListener(ProcessarPedidosUseCase useCase) {
        this.useCase = useCase;
    }

    @RabbitListener(queues = "orderhub-pedido-queue")
    public void consumir(CriarPedidoDTO dto) throws Exception {
        useCase.executar(dto);
    }
}