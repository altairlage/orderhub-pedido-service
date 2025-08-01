package com.fiap.orderhub.orderhub_pedido_service.configurations;

import com.fiap.orderhub.orderhub_pedido_service.domain.ItemPedido;

import java.util.List;

public interface EstoqueClient {
    void baixarEstoque(List<ItemPedido> produtos);
    void reporEstoque(List<ItemPedido> produtos);
}