package com.fiap.orderhub.orderhub_pedido_service.dto;

import br.com.orderhub.core.domain.enums.StatusPagamento;

public record AtualizarStatusPedidoDto (Long idPedido,
        StatusPagamento statusPagamento) {

}
