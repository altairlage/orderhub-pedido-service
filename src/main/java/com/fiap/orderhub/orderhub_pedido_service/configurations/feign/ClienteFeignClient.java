package com.fiap.orderhub.orderhub_pedido_service.configurations.feign;

import com.fiap.orderhub.orderhub_pedido_service.dto.ClienteApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-service", url = "${cliente.service.url}")
public interface ClienteFeignClient {
    @GetMapping("/clientes/id/{id}")
    ClienteApiResponseDto buscarClientePorId(@PathVariable("id") Long id);
}

