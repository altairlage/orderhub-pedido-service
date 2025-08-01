package com.fiap.orderhub.orderhub_pedido_service.configurations;

import com.fiap.orderhub.orderhub_pedido_service.domain.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteClient {
    public Cliente buscarCliente(Long idCliente) {
        return new Cliente(idCliente, "Nome do Cliente");
    }
}