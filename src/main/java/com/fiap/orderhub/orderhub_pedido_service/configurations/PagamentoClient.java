package com.fiap.orderhub.orderhub_pedido_service.configurations;

import com.fiap.orderhub.orderhub_pedido_service.domain.Pedido;

public class PagamentoClient {
    public boolean efetuarPagamento(Pedido pedido) {
        return true;
    }
}