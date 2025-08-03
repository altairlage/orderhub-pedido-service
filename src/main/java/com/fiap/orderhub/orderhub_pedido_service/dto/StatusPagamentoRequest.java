package com.fiap.orderhub.orderhub_pedido_service.dto;

import br.com.orderhub.core.domain.enums.StatusPedido;

public record StatusPagamentoRequest(
        Long idPedido,
        StatusPedido novoStatus
) {}
