package com.fiap.orderhub.orderhub_pedido_service.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {
    private Long id;
    private Integer quantidade;

    public Produto(Long id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public Long getId() { return id; }
    public int getQuantidade() { return quantidade; }
}