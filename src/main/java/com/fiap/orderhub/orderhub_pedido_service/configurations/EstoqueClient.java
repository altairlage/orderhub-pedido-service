package com.fiap.orderhub.orderhub_pedido_service.configurations;

import com.fiap.orderhub.orderhub_pedido_service.domain.ItemPedido;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EstoqueClient {
    void baixarEstoque(List<ItemPedido> produtos);
    void reporEstoque(List<ItemPedido> produtos);
}