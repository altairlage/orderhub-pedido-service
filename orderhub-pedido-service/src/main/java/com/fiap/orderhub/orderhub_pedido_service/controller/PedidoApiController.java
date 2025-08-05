package com.fiap.orderhub.orderhub_pedido_service.controller;

import com.fiap.orderhub.orderhub_pedido_service.dto.AtualizacaoStatusPedidoApiRequestDto;
import com.fiap.orderhub.orderhub_pedido_service.dto.AtualizarStatusPedidoDto;
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
    public ResponseEntity<Void> atualizarStatus(@RequestBody AtualizarStatusPedidoDto atualizarStatusPedidoDto) {
        AtualizacaoStatusPedidoApiRequestDto atualizacaoStatusPedidoApiRequestDto = new AtualizacaoStatusPedidoApiRequestDto(
                atualizarStatusPedidoDto.idPedido(),
                atualizarStatusPedidoDto.statusPagamento()
        );
        orquestradorAtualizacaoPedido.atualizarStatusPedido(atualizacaoStatusPedidoApiRequestDto);
        return ResponseEntity.ok().build();
    }
}