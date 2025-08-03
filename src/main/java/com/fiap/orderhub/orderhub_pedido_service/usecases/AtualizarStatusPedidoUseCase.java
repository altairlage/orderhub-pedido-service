package com.fiap.orderhub.orderhub_pedido_service.usecases;

import br.com.orderhub.core.domain.entities.Pedido;
import br.com.orderhub.core.domain.enums.StatusPedido;
import com.fiap.orderhub.orderhub_pedido_service.dto.StatusPagamentoRequest;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarStatusPedidoUseCase {
    private final PedidoRepository pedidoRepository;

    public AtualizarStatusPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void executar(StatusPagamentoRequest request) {
        Pedido pedidoEntity = pedidoRepository.findById(request.idPedido()).orElse(null);
        if (pedidoEntity == null) {
            throw new RuntimeException("Pedido não encontrado");
        }
        pedidoEntity.setStatus(StatusPedido.valueOf(request.novoStatus().name()));
        pedidoRepository.save(pedidoEntity);
    }
}