package com.fiap.orderhub.orderhub_pedido_service.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private Long id;
    private String nome;

    public Cliente(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
}