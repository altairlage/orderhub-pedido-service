package com.fiap.orderhub.orderhub_pedido_service.usecases;

import com.fiap.orderhub.orderhub_pedido_service.domain.Pedido;
import com.fiap.orderhub.orderhub_pedido_service.domain.PedidoRepository;
import com.fiap.orderhub.orderhub_pedido_service.controller.StatusPagamentoRequest;
import org.springframework.stereotype.Service;

@Service
public class AtualizarStatusPedidoUseCase {
    private final PedidoRepository pedidoRepository;

    public AtualizarStatusPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void executar(StatusPagamentoRequest request) {
        Pedido pedido = pedidoRepository.buscarPorId(request.getIdPedido());
        if (pedido == null) {
            throw new RuntimeException("Pedido não encontrado");
        }
        pedido.setStatus(request.getNovoStatus());
        pedidoRepository.salvar(pedido);
    }
}
