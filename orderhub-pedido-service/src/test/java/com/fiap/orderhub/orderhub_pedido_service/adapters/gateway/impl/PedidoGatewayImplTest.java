package com.fiap.orderhub.orderhub_pedido_service.adapters.gateway.impl;

import br.com.orderhub.core.domain.entities.Pedido;
import br.com.orderhub.core.domain.enums.StatusPedido;
import com.fiap.orderhub.orderhub_pedido_service.gateway.impl.PedidoGatewayImpl;
import com.fiap.orderhub.orderhub_pedido_service.mapper.PedidoEntityMapper;
import com.fiap.orderhub.orderhub_pedido_service.persistence.ItemPedidoEntity;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoEntity;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoGatewayImplTest {

    @InjectMocks
    private PedidoGatewayImpl pedidoGateway;

    @Mock
    private PedidoRepository pedidoRepository;

    private PedidoEntity pedidoEntity;
    private Pedido pedidoDomain;

    @BeforeEach
    void setup() {
        ItemPedidoEntity itemPedidoEntity1 = new ItemPedidoEntity();
        ItemPedidoEntity itemPedidoEntity2 = new ItemPedidoEntity();

        Map<String, Object> dtoMap1 = new HashMap<>();
        dtoMap1.put("quantidade", 1);
        dtoMap1.put("idProduto", 1);

        Map<String, Object> dtoMap2 = new HashMap<>();
        dtoMap2.put("quantidade", 1);
        dtoMap2.put("idProduto", 2);

        pedidoEntity = new PedidoEntity(
                1L,
                1L,
                1L,
                Arrays.asList(itemPedidoEntity1, itemPedidoEntity2),
                StatusPedido.ABERTO
        );

        pedidoDomain = new Pedido(

                1L,
                1L,
                1L,
                Arrays.asList(dtoMap1, dtoMap2),
                StatusPedido.ABERTO
        );
    }

    @Test
    void deveBuscarPedidoPorIdComSucesso() {
        try (MockedStatic<PedidoEntityMapper> mapper = Mockito.mockStatic(PedidoEntityMapper.class)) {
            mapper.when(() -> PedidoEntityMapper.entityToDomain(pedidoEntity)).thenReturn(pedidoDomain);
            when(pedidoRepository.findById(String.valueOf(1L))).thenReturn(Optional.of(pedidoEntity));

            Pedido result = pedidoGateway.buscarPorId(1L);

            assertNotNull(result);
            assertEquals(StatusPedido.ABERTO, result.getStatus());
        }
    }

    @Test
    void deveRetornarNullQuandoPedidoNaoEncontrado() {
        when(pedidoRepository.findById("1")).thenReturn(Optional.empty());

        Pedido result = pedidoGateway.buscarPorId(1L);

        assertNull(result);
    }

    @Test
    void deveBuscarPedidosPorIdCliente() {
        List<PedidoEntity> entities = List.of(pedidoEntity);
        List<Pedido> pedidos = List.of(pedidoDomain);

        try (MockedStatic<PedidoEntityMapper> mapper = Mockito.mockStatic(PedidoEntityMapper.class)) {
            when(pedidoRepository.findByIdCliente(1L)).thenReturn(entities);
            mapper.when(() -> PedidoEntityMapper.mapListToPedidoList(entities)).thenReturn(pedidos);

            List<Pedido> result = pedidoGateway.buscarPorIdCliente(1L);

            assertEquals(1, result.size());
        }
    }

    @Test
    void deveCriarPedidoComSucesso() {
        try (MockedStatic<PedidoEntityMapper> mapper = Mockito.mockStatic(PedidoEntityMapper.class)) {
            mapper.when(() -> PedidoEntityMapper.domainToEntity(pedidoDomain)).thenReturn(pedidoEntity);
            mapper.when(() -> PedidoEntityMapper.entityToDomainCreation(pedidoEntity)).thenReturn(pedidoDomain);
            when(pedidoRepository.save(pedidoEntity)).thenReturn(pedidoEntity);

            Pedido result = pedidoGateway.criar(pedidoDomain);

            assertNotNull(result);
            assertEquals(StatusPedido.ABERTO, result.getStatus());
        }
    }

    @Test
    void deveEditarStatusDoPedido() {
        try (MockedStatic<PedidoEntityMapper> mapper = Mockito.mockStatic(PedidoEntityMapper.class)) {
            when(pedidoRepository.findById("1")).thenReturn(Optional.of(pedidoEntity));
            when(pedidoRepository.save(any())).thenReturn(pedidoEntity);
            mapper.when(() -> PedidoEntityMapper.entityToDomain(pedidoEntity)).thenReturn(pedidoDomain);

            Pedido result = pedidoGateway.editarStatus(1L, StatusPedido.FECHADO_SUCESSO);

            assertNotNull(result);
            assertEquals(StatusPedido.ABERTO, result.getStatus());
        }
    }

    @Test
    void deveListarPedidosAbertos() {
        List<PedidoEntity> allPedidos = List.of(pedidoEntity);
        List<Pedido> pedidos = List.of(pedidoDomain);

        try (MockedStatic<PedidoEntityMapper> mapper = Mockito.mockStatic(PedidoEntityMapper.class)) {
            when(pedidoRepository.findAll()).thenReturn(allPedidos);
            mapper.when(() -> PedidoEntityMapper.mapListToPedidoList(List.of(pedidoEntity))).thenReturn(pedidos);

            List<Pedido> result = pedidoGateway.listarTodos();

            assertEquals(1, result.size());
        }
    }
}