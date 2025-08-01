package com.fiap.orderhub.orderhub_pedido_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoRequestDTO {
    private Long idCliente;
    private List<Long> idsProdutos;
}
