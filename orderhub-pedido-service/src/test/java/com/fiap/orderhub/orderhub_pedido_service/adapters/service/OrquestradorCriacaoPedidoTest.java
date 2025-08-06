package com.fiap.orderhub.orderhub_pedido_service.adapters.service;

import br.com.orderhub.core.domain.enums.StatusPagamento;
import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.dto.pagamentos.PagamentoDTO;
import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fiap.orderhub.orderhub_pedido_service.dto.ClienteApiResponseDto;
import com.fiap.orderhub.orderhub_pedido_service.dto.EstoqueApiResponseDto;
import com.fiap.orderhub.orderhub_pedido_service.dto.ProdutoApiResponseDto;
import com.fiap.orderhub.orderhub_pedido_service.service.OrquestradorCriacaoPedido;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class OrquestradorCriacaoPedidoTest {

    @Autowired
    private OrquestradorCriacaoPedido orquestrador;

    private static MockWebServer mockWebServer;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("cliente.service.url", () -> mockWebServer.url("/").toString());
        registry.add("produto.service.url", () -> mockWebServer.url("/").toString());
        registry.add("estoque.service.url", () -> mockWebServer.url("/").toString());
        registry.add("pagamento.service.url", () -> mockWebServer.url("/").toString());
    }

    @Test
    void deveCriarPedidoComSucessoOrquestrandoTodosOsServicos() throws Exception {
        CriarPedidoDTO criarPedidoDTO = new CriarPedidoDTO(1L, List.of(Map.of("idProduto", 101L, "quantidade", 2)), StatusPedido.ABERTO);

        ClienteApiResponseDto clienteResponse = new ClienteApiResponseDto(1L, "Cliente Teste", "123.456.789-00", null, null, null, "teste@teste.com", null);
        ProdutoApiResponseDto produtoResponse = new ProdutoApiResponseDto(101L, "Produto Teste", "Desc", 50.0);
        EstoqueApiResponseDto estoqueResponse = new EstoqueApiResponseDto(1L, 98, LocalDateTime.now(), LocalDateTime.now());
        PagamentoDTO pagamentoResponse = new PagamentoDTO(201L, 1L, "Cliente Teste", "teste@teste.com", 100.0, StatusPagamento.EM_ABERTO);

        mockWebServer.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(clienteResponse)).addHeader("Content-Type", "application/json"));
        mockWebServer.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(produtoResponse)).addHeader("Content-Type", "application/json"));
        mockWebServer.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(estoqueResponse)).addHeader("Content-Type", "application/json"));
        mockWebServer.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(pagamentoResponse)).addHeader("Content-Type", "application/json"));

        assertDoesNotThrow(() -> orquestrador.criarPedido(criarPedidoDTO));
    }

    @Test
    void deveLancarExcecaoQuandoClienteServiceFalhar() {
        CriarPedidoDTO criarPedidoDTO = new CriarPedidoDTO(99L, List.of(Map.of("idProduto", 101L, "quantidade", 2)), StatusPedido.ABERTO);
        mockWebServer.enqueue(new MockResponse().setResponseCode(404));

        assertThrows(WebClientResponseException.NotFound.class, () -> orquestrador.criarPedido(criarPedidoDTO));
    }

    @Test
    void deveLancarExcecaoQuandoProdutoServiceFalhar() throws Exception {
        CriarPedidoDTO criarPedidoDTO = new CriarPedidoDTO(1L, List.of(Map.of("idProduto", 999L, "quantidade", 2)), StatusPedido.ABERTO);
        ClienteApiResponseDto clienteResponse = new ClienteApiResponseDto(1L, "Cliente Teste", "123.456.789-00", null, null, null, "teste@teste.com", null);

        mockWebServer.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(clienteResponse)).addHeader("Content-Type", "application/json"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(404));

        assertThrows(WebClientResponseException.NotFound.class, () -> orquestrador.criarPedido(criarPedidoDTO));
    }
}
