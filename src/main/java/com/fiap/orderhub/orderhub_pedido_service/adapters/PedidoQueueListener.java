package com.fiap.orderhub.orderhub_pedido_service.adapters;

import com.fiap.orderhub.orderhub_pedido_service.dto.PedidoRequestDTO;
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
    public void consumir(PedidoRequestDTO dto) {
        useCase.executar(dto);
    }
}