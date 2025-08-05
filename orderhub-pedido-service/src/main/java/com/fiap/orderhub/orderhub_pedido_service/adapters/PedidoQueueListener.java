package com.fiap.orderhub.orderhub_pedido_service.adapters;

import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import com.fiap.orderhub.orderhub_pedido_service.service.OrquestradorCriacaoPedido;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component

public class PedidoQueueListener {
    private final OrquestradorCriacaoPedido criacaoPedido;

    public PedidoQueueListener(OrquestradorCriacaoPedido criacaoPedido) {
        this.criacaoPedido = criacaoPedido;
    }

    @RabbitListener(queues = "pedido-receiver-queue")

    public void consumir(CriarPedidoDTO dto) {
        criacaoPedido.criarPedido(dto);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}