package com.fiap.orderhub.orderhub_pedido_service.adapters;

import br.com.orderhub.core.controller.PedidoController;
import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import br.com.orderhub.core.dto.pedidos.PedidoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.orderhub.orderhub_pedido_service.controller.PedidoApiController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(PedidoApiController.class)
public class PedidoApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoController pedidoController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarPedidoComSucesso() throws Exception {
        PedidoDTO mockResponse = new PedidoDTO(1L, null, null, List.of(), null);
        Mockito.when(pedidoController.criarPedido(any(CriarPedidoDTO.class))).thenReturn(mockResponse);

        CriarPedidoDTO request = new CriarPedidoDTO(1L, List.of(Map.of("idProduto", 10L, "quantidade", 2)), null);

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}