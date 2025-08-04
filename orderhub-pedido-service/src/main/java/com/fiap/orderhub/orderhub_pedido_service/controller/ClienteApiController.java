package com.fiap.orderhub.orderhub_pedido_service.controller;

import br.com.orderhub.core.domain.entities.Cliente;
import com.fiap.orderhub.orderhub_pedido_service.gateway.impl.ClienteGatewayImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos/clientes")
@AllArgsConstructor
public class ClienteApiController {

    private final ClienteGatewayImpl clienteGateway;

    @GetMapping("/{id}")
    public Cliente buscarClientePorId(@PathVariable Long id) {
        return clienteGateway.buscarPorId(id);
    }
}
