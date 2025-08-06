package br.com.orderhub.pedido_service.adapter.gateway;

import br.com.orderhub.core.domain.entities.Pedido;
import br.com.orderhub.core.domain.enums.StatusPedido;
import com.fiap.orderhub.orderhub_pedido_service.gateway.impl.PedidoGatewayImpl;
import com.fiap.orderhub.orderhub_pedido_service.mapper.PedidoEntityMapper;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoEntity;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PedidoGatewayImplTest {

    private PedidoRepository pedidoRepository;
    private PedidoEntityMapper mapper;
    private PedidoGatewayImpl gateway;

    @BeforeEach
    void setup() {
        pedidoRepository = mock(PedidoRepository.class);
        mapper = new PedidoEntityMapper();
        gateway = new PedidoGatewayImpl(pedidoRepository);
    }

    @Test
    void buscarPorId_DeveRetornarPedido() {
        PedidoEntity entity = new PedidoEntity();
        entity.setIdPedido(1L);
        entity.setStatus(StatusPedido.ABERTO);

        when(pedidoRepository.findById("1")).thenReturn(Optional.of(entity));

        Pedido pedido = gateway.buscarPorId(1L);
        assertNotNull(pedido);
        assertEquals(1L, pedido.getIdPedido());
    }

    @Test
    void buscarPorId_DeveRetornarNull() {
        when(pedidoRepository.findById("1")).thenReturn(Optional.empty());
        assertNull(gateway.buscarPorId(1L));
    }
}