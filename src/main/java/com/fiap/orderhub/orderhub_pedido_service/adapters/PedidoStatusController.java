package com.fiap.orderhub.orderhub_pedido_service.adapters;

import com.fiap.orderhub.orderhub_pedido_service.dto.StatusPagamentoRequest;
import com.fiap.orderhub.orderhub_pedido_service.usecases.AtualizarStatusPedidoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos/status")
public class PedidoStatusController {

    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    public PedidoStatusController(AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase) {
        this.atualizarStatusPedidoUseCase = atualizarStatusPedidoUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> atualizarStatus(@RequestBody StatusPagamentoRequest request) {
        atualizarStatusPedidoUseCase.executar(request);
        return ResponseEntity.ok().build();
    }
}