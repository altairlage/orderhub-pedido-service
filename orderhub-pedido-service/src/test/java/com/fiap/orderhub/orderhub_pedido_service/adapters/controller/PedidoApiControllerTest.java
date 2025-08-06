package com.fiap.orderhub.orderhub_pedido_service.adapters.controller;

import br.com.orderhub.core.domain.entities.Pedido;
import br.com.orderhub.core.domain.enums.StatusPagamento;
import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.dto.pedidos.PedidoDTO;
import br.com.orderhub.core.interfaces.IPedidoGateway;
import com.fiap.orderhub.orderhub_pedido_service.controller.PedidoApiController;
import com.fiap.orderhub.orderhub_pedido_service.dto.AtualizarStatusPedidoDto;
import com.fiap.orderhub.orderhub_pedido_service.service.OrquestradorAtualizacaoPedido;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoApiController.class)
class PedidoApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPedidoGateway pedidoGateway;

    @MockBean
    private OrquestradorAtualizacaoPedido orquestradorAtualizacaoPedido;

    @Test
    void deveAtualizarStatusDoPedido() throws Exception {
        Map<String, Object> dtoMap1 = new HashMap<>();
        dtoMap1.put("quantidade", 1);
        dtoMap1.put("idProduto", 1);

        Map<String, Object> dtoMap2 = new HashMap<>();
        dtoMap2.put("quantidade", 1);
        dtoMap2.put("idProduto", 2);

        AtualizarStatusPedidoDto dto = new AtualizarStatusPedidoDto(1L, StatusPagamento.FECHADO_COM_SUCESSO);
        PedidoDTO pedidoDTO = new PedidoDTO(
                1L,
                1L,
                1L,
                Arrays.asList(dtoMap1, dtoMap2),
                StatusPedido.FECHADO_SUCESSO
                );

        when(orquestradorAtualizacaoPedido.atualizarStatusPedido(any())).thenReturn(pedidoDTO);

        mockMvc.perform(put("/pedidos/atualizarStatusPedido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "idPedido": 1,
                        "status": "FECHADO_SUCESSO"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPedido").value(1))
                .andExpect(jsonPath("$.status").value("FECHADO_SUCESSO"));
    }

    @Test
    void deveBuscarPedidoPorId() throws Exception {
        Map<String, Object> dtoMap1 = new HashMap<>();
        dtoMap1.put("quantidade", 1);
        dtoMap1.put("idProduto", 1);

        Map<String, Object> dtoMap2 = new HashMap<>();
        dtoMap2.put("quantidade", 1);
        dtoMap2.put("idProduto", 2);

        AtualizarStatusPedidoDto dto = new AtualizarStatusPedidoDto(1L, StatusPagamento.FECHADO_COM_SUCESSO);
        PedidoDTO pedidoDTO = new PedidoDTO(
                1L,
                1L,
                1L,
                Arrays.asList(dtoMap1, dtoMap2),
                StatusPedido.FECHADO_SUCESSO
        );

        Pedido pedido = new Pedido(
                1L,
                1L,
                1L,
                Arrays.asList(dtoMap1, dtoMap2),
                StatusPedido.FECHADO_SUCESSO
        );

        when(pedidoGateway.buscarPorId(1L)).thenReturn(pedido);

        mockMvc.perform(get("/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPedido").value(1))
                .andExpect(jsonPath("$.status").value("FECHADO_SUCESSO"));
    }
}
