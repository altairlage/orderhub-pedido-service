package com.fiap.orderhub.orderhub_pedido_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ItemPedido {
    private BigDecimal preco;
    private Integer quantidade;
}
