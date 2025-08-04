package com.fiap.orderhub.orderhub_pedido_service.dto;

import br.com.orderhub.core.domain.enums.StatusPagamento;

public record AtualizacaoStatusPeditoApiRequestDto(Long idPedido, StatusPagamento statusPagamento) {
}
