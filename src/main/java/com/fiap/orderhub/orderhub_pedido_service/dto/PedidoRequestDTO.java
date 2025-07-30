package com.fiap.orderhub.orderhub_pedido_service.dto;

import java.util.List;

public class PedidoRequestDTO {
    private Long idCliente;
    private List<Long> idsProdutos;

    public PedidoRequestDTO() {}

    public PedidoRequestDTO(Long idCliente, List<Long> idsProdutos) {
        this.idCliente = idCliente;
        this.idsProdutos = idsProdutos;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<Long> getIdsProdutos() {
        return idsProdutos;
    }

    public void setIdsProdutos(List<Long> idsProdutos) {
        this.idsProdutos = idsProdutos;
    }
}
