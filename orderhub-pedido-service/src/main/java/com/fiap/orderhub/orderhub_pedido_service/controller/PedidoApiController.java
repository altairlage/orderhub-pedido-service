package com.fiap.orderhub.orderhub_pedido_service.controller;

import br.com.orderhub.core.dto.pedidos.PedidoDTO;
import com.fiap.orderhub.orderhub_pedido_service.dto.AtualizacaoStatusPeditoApiRequestDto;
import com.fiap.orderhub.orderhub_pedido_service.service.OrquestradorAtualizacaoPedido;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoApiController {

    private final OrquestradorAtualizacaoPedido orquestradorAtualizacaoPedido;

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarStatus(@RequestBody PedidoDTO pedidoDTO) {
        atualizarStatusPedidoUseCase.executar(pedidoDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarStatus(@RequestBody AtualizacaoStatusPeditoApiRequestDto atualizacaoPeditoApiRequestDto) {


        return ResponseEntity.ok().build();
    }
}