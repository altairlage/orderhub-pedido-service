//package com.fiap.orderhub.orderhub_pedido_service.adapters.api.handler;
//
//import br.com.orderhub.core.domain.enums.StatusPedido;
//import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
//import com.fiap.orderhub.orderhub_pedido_service.adapters.PedidoQueueListener;
//import com.fiap.orderhub.orderhub_pedido_service.service.OrquestradorCriacaoPedido;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@SpringBootTest
//class PedidoQueueListenerTest {
//
//    @InjectMocks
//    private PedidoQueueListener listener;
//
//    @InjectMocks
//    private OrquestradorCriacaoPedido criacaoPedido;
//
//    @Test
//    void deveConsumirMensagemEDispararCriacaoDePedido() {
//        Map<String, Object> dtoMap1 = new HashMap<>();
//        dtoMap1.put("quantidade", 1);
//        dtoMap1.put("idProduto", 1);
//
//        Map<String, Object> dtoMap2 = new HashMap<>();
//        dtoMap2.put("quantidade", 1);
//        dtoMap2.put("idProduto", 2);
//        CriarPedidoDTO dto = new CriarPedidoDTO(
//                123L,
//                Arrays.asList(dtoMap1, dtoMap2),
//                StatusPedido.ABERTO
//        );
//
//        // Act
//        listener.consumir(dto);
//
//        // Assert
//        ArgumentCaptor<CriarPedidoDTO> captor = ArgumentCaptor.forClass(CriarPedidoDTO.class);
//        verify(criacaoPedido, times(1)).criarPedido(captor.capture());
//
//        CriarPedidoDTO capturado = captor.getValue();
//        assertEquals(123L, capturado.idCliente());
//        assertEquals(1, capturado.listaQtdProdutos().size());
//    }
//}
