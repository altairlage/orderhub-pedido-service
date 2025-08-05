package com.fiap.orderhub.orderhub_pedido_service.configuration;

import br.com.orderhub.core.controller.PedidoController;
import br.com.orderhub.core.interfaces.IClienteGateway;
import br.com.orderhub.core.interfaces.IPedidoGateway;
import br.com.orderhub.core.interfaces.IProdutoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoControllerConfig {
    @Bean
    public PedidoController pedidoController(IPedidoGateway pedidoGateway){
        return new PedidoController(pedidoGateway);
    }
}
