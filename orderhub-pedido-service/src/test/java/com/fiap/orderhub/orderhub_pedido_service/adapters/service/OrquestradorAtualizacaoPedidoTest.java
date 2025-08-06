package com.fiap.orderhub.orderhub_pedido_service.adapters.service;

import br.com.orderhub.core.controller.PedidoController;
import br.com.orderhub.core.domain.enums.StatusPagamento;
import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.dto.pedidos.PedidoDTO;
import br.com.orderhub.core.exceptions.EstoqueNaoEncontradoException;
import com.fiap.orderhub.orderhub_pedido_service.dto.AtualizacaoStatusPedidoApiRequestDto;
import com.fiap.orderhub.orderhub_pedido_service.dto.EstoqueApiRequestDto;
import com.fiap.orderhub.orderhub_pedido_service.dto.EstoqueApiResponseDto;
import com.fiap.orderhub.orderhub_pedido_service.service.OrquestradorAtualizacaoPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrquestradorAtualizacaoPedidoTest {

    @Mock
    private PedidoController pedidoController;

    @InjectMocks
    private OrquestradorAtualizacaoPedido orquestrador;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(orquestrador, "estoqueServiceUrl", "http://localhost:8080");
    }

    @Test
    void deveAtualizarStatusParaFechadoSemCreditoEReporEstoqueComSucesso() {
        PedidoController pedidoController = mock(PedidoController.class);

        OrquestradorAtualizacaoPedido orquestrador = Mockito.spy(new OrquestradorAtualizacaoPedido(pedidoController));

        AtualizacaoStatusPedidoApiRequestDto requestDto =
                new AtualizacaoStatusPedidoApiRequestDto(1L, StatusPagamento.FECHADO_FALHA_PAGAMENTO);

        Map<String, Object> item = new HashMap<>();
        item.put("idProduto", 10L);
        item.put("quantidade", 2);

        PedidoDTO pedidoDTO = new PedidoDTO(
                1L,
                100L,
                200L,
                List.of(item),
                StatusPedido.ABERTO
        );

        PedidoDTO pedidoAtualizado = new PedidoDTO(
                1L,
                100L,
                200L,
                List.of(item),
                StatusPedido.FECHADO_SEM_CREDITO
        );

        EstoqueApiRequestDto estoqueRequest = new EstoqueApiRequestDto(
                2
        );
        EstoqueApiResponseDto estoqueResponse = new EstoqueApiResponseDto(10L, 2, LocalDateTime.now(), LocalDateTime.now());

        // Mocka chamadas
        when(pedidoController.buscarPedidoPorId(1L)).thenReturn(pedidoDTO);
        when(pedidoController.editarPedidoStatus(1L, StatusPedido.FECHADO_SEM_CREDITO)).thenReturn(pedidoAtualizado);
        doReturn(estoqueResponse).when(orquestrador).reporEstoque(eq(10L), eq(estoqueRequest));

        // Act
        PedidoDTO result = orquestrador.atualizarStatusPedido(requestDto);

        // Assert
        assertNotNull(result);
        assertEquals(StatusPedido.FECHADO_SEM_CREDITO, result.status());
    }

    @Test
    void deveAtualizarStatusParaFechadoSucesso() {
        Map<String, Object> item = new HashMap<>();
        item.put("idProduto", 10L);
        item.put("quantidade", 2);

        AtualizacaoStatusPedidoApiRequestDto requestDto = new AtualizacaoStatusPedidoApiRequestDto(2L, StatusPagamento.FECHADO_COM_SUCESSO);

        PedidoDTO pedidoDTO = new PedidoDTO(
                2L,
                100L,
                200L,
                List.of(item),
                StatusPedido.ABERTO);

        when(pedidoController.buscarPedidoPorId(2L)).thenReturn(pedidoDTO);
        when(pedidoController.editarPedidoStatus(2L, StatusPedido.FECHADO_SUCESSO)).thenReturn(pedidoDTO);

        PedidoDTO result = orquestrador.atualizarStatusPedido(requestDto);

        assertNotNull(result);
        assertEquals(2L, result.idPedido());
    }
}

