package com.fiap.orderhub.orderhub_pedido_service.adapters.configuration;

import br.com.orderhub.core.controller.PedidoController;
import br.com.orderhub.core.interfaces.IPedidoGateway;
import com.fiap.orderhub.orderhub_pedido_service.configuration.PedidoControllerConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@Import(PedidoControllerConfig.class)
class PedidoControllerConfigTest {

    @MockBean
    private IPedidoGateway pedidoGateway;

    @Autowired
    private PedidoController pedidoController;

    @Test
    void deveInstanciarPedidoControllerComGatewayMockado() {
        assertNotNull(pedidoController);
    }
}

