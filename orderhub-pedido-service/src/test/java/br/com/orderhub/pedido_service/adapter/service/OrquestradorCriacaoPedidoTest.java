package br.com.orderhub.pedido_service.adapter.service;

import br.com.orderhub.core.controller.PedidoController;
import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import br.com.orderhub.core.dto.pedidos.PedidoDTO;
import com.fiap.orderhub.orderhub_pedido_service.service.OrquestradorCriacaoPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrquestradorCriacaoPedidoTest {

    private PedidoController pedidoController;
    private OrquestradorCriacaoPedido orquestrador;

    @BeforeEach
    void setup() {
        pedidoController = mock(PedidoController.class);
        orquestrador = new OrquestradorCriacaoPedido(pedidoController);
    }

    @Test
    void deveCriarPedido() {
        CriarPedidoDTO dto = new CriarPedidoDTO(1L, List.of(Map.of("idProduto", 10L, "quantidade", 2)), null);
        PedidoDTO response = new PedidoDTO(1L, null, null, List.of(), null);

        when(pedidoController.criarPedido(dto)).thenReturn(response);

        orquestrador.criarPedido(dto);
    }
}