package com.fiap.orderhub.orderhub_pedido_service.controller;

import com.fiap.orderhub.orderhub_pedido_service.configurations.feign.ClienteFeignClient;
import com.fiap.orderhub.orderhub_pedido_service.dto.ClienteApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteApiController {
    private final ClienteFeignClient clienteFeignClient;

    @GetMapping("/id/{id}")
    public ClienteApiResponseDto buscarClientePorId(@PathVariable Long id) {
        return clienteFeignClient.buscarClientePorId(id);
    }
}
