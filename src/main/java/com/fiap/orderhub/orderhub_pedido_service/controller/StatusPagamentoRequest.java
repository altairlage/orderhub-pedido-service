package com.fiap.orderhub.orderhub_pedido_service.controller;

import com.fiap.orderhub.orderhub_pedido_service.domain.StatusPedido;

public class StatusPagamentoRequest {
    private Long idPedido;
    private StatusPedido novoStatus;

    public StatusPagamentoRequest() {}

    public StatusPagamentoRequest(Long idPedido, StatusPedido novoStatus) {
        this.idPedido = idPedido;
        this.novoStatus = novoStatus;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public StatusPedido getNovoStatus() {
        return novoStatus;
    }

    public void setNovoStatus(StatusPedido novoStatus) {
        this.novoStatus = novoStatus;
    }
}

