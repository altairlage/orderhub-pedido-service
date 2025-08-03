package com.fiap.orderhub.orderhub_pedido_service.configuration.feign;

import br.com.orderhub.core.dto.clientes.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-service", url = "${cliente-service.url}")
public interface ClienteFeignClient {
    @GetMapping("/clientes/id/{id}")
    ClienteDTO buscarClientePorId(@PathVariable("id") Long id);
}
