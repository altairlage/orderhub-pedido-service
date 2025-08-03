package com.fiap.orderhub.orderhub_pedido_service.configuration;

import br.com.orderhub.core.controller.EstoqueController;
import br.com.orderhub.core.interfaces.IEstoqueGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EstoqueControllerConfig {
    @Bean
    public EstoqueController estoqueController(IEstoqueGateway estoqueGateway){
        return new EstoqueController(estoqueGateway);
    }
}
