package com.fiap.orderhub.orderhub_pedido_service.controller;

import com.fiap.orderhub.orderhub_pedido_service.configurations.feign.ClienteFeignClient;
import com.fiap.orderhub.orderhub_pedido_service.dto.ClienteApiResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteApiController {
    private final ClienteFeignClient clienteFeignClient;

    public ClienteApiController(ClienteFeignClient clienteFeignClient) {
        this.clienteFeignClient = clienteFeignClient;
    }

    @GetMapping("/id/{id}")
    public ClienteApiResponseDto buscarClientePorId(@PathVariable Long id) {
        return clienteFeignClient.buscarClientePorId(id);
    }
}
