package com.fiap.orderhub.orderhub_pedido_service.controller;

import br.com.orderhub.core.domain.presenters.PedidoPresenter;
import br.com.orderhub.core.dto.pagamentos.PagamentoDTO;
import br.com.orderhub.core.dto.pedidos.PedidoDTO;
import br.com.orderhub.core.interfaces.IPedidoGateway;
import com.fiap.orderhub.orderhub_pedido_service.dto.AtualizacaoStatusPedidoApiRequestDto;
import com.fiap.orderhub.orderhub_pedido_service.dto.AtualizarStatusPedidoDto;
import com.fiap.orderhub.orderhub_pedido_service.mapper.PedidoEntityMapper;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoEntity;
import com.fiap.orderhub.orderhub_pedido_service.service.OrquestradorAtualizacaoPedido;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoApiController {
    private final IPedidoGateway pedidoGateway;

    private final OrquestradorAtualizacaoPedido orquestradorAtualizacaoPedido;

    @PutMapping("/atualizarStatusPedido")
    public ResponseEntity<PedidoDTO> atualizarStatus(@RequestBody AtualizarStatusPedidoDto atualizarStatusPedidoDto) {
        System.out.println("REQUEST DE ATUALIZAÇÃO DE STATUS DE PEDIDO RECEBIDA: " + atualizarStatusPedidoDto);
        AtualizacaoStatusPedidoApiRequestDto atualizacaoStatusPedidoApiRequestDto = new AtualizacaoStatusPedidoApiRequestDto(
                atualizarStatusPedidoDto.idPedido(),
                atualizarStatusPedidoDto.statusPagamento()
        );
        return ResponseEntity.ok(orquestradorAtualizacaoPedido.atualizarStatusPedido(atualizacaoStatusPedidoApiRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPedidoPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(PedidoPresenter.ToDTO(pedidoGateway.buscarPorId(id)));
    }
}