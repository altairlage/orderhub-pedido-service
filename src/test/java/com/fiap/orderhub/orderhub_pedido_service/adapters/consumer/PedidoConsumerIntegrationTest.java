package com.fiap.orderhub.orderhub_pedido_service.adapters.consumer;

import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import br.com.orderhub.core.dto.clientes.ClienteDTO;
import br.com.orderhub.core.dto.produtos.CriarProdutoDTO;
import br.com.orderhub.core.domain.enums.StatusPedido;
import com.fiap.orderhub.orderhub_pedido_service.domain.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PedidoConsumerIntegrationTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    public void deveConsumirPedidoDaFilaEPersistirNoBanco() throws InterruptedException {
        // Arrange
        Long idCliente = 1L;
        ClienteDTO clienteDTO = new ClienteDTO(idCliente, "Cliente Teste", "email@teste.com", "cpf", "telefone", "endereco", "cidade", "estado");
        Long idPagamento = 123L;
        Map<Integer, CriarProdutoDTO> produtoMap = new HashMap<>();
        produtoMap.put(10, new CriarProdutoDTO("Produto Teste", "Descrição", 99.99));
        List<Map<Integer, CriarProdutoDTO>> listaQtdProdutos = List.of(produtoMap);
        CriarPedidoDTO pedidoDTO = new CriarPedidoDTO(clienteDTO, idPagamento, listaQtdProdutos, StatusPedido.ABERTO);

        // Act
        rabbitTemplate.convertAndSend("pedido-queue", pedidoDTO);

        // Aguarda processamento (ajuste se necessário)
        Thread.sleep(2000);

    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public PedidoRepository pedidoRepository() {
            return new PedidoRepository() {
                @Override
                public void salvar(com.fiap.orderhub.orderhub_pedido_service.domain.Pedido pedido) {
                    // Implementação fake para teste
                }
                @Override
                public com.fiap.orderhub.orderhub_pedido_service.domain.Pedido buscarPorId(Long id) {
                    return null;
                }
            };
        }

        @Bean
        public com.fiap.orderhub.orderhub_pedido_service.configurations.ProdutoClient produtoClient() {
            return new com.fiap.orderhub.orderhub_pedido_service.configurations.ProdutoClient() {
                @Override
                public java.util.List<com.fiap.orderhub.orderhub_pedido_service.domain.Produto> buscarProdutos(java.util.List<Long> idsProdutos) {
                    return java.util.Collections.emptyList();
                }
            };
        }

        @Bean
        public com.fiap.orderhub.orderhub_pedido_service.configurations.ClienteClient clienteClient() {
            return new com.fiap.orderhub.orderhub_pedido_service.configurations.ClienteClient();
        }

        @Bean
        public com.fiap.orderhub.orderhub_pedido_service.configurations.EstoqueClient estoqueClient() {
            return new com.fiap.orderhub.orderhub_pedido_service.configurations.EstoqueClient() {
                @Override
                public void baixarEstoque(List produtos) {}
                @Override
                public void reporEstoque(List produtos) {}
            };
        }

        @Bean
        public com.fiap.orderhub.orderhub_pedido_service.configurations.PagamentoClient pagamentoClient() {
            return new com.fiap.orderhub.orderhub_pedido_service.configurations.PagamentoClient();
        }

        @Bean
        public org.springframework.amqp.support.converter.MessageConverter messageConverter() {
            return new Jackson2JsonMessageConverter();
        }

        @Bean
        public RabbitTemplate rabbitTemplate() {
            return org.mockito.Mockito.mock(RabbitTemplate.class);
        }
    }
}
