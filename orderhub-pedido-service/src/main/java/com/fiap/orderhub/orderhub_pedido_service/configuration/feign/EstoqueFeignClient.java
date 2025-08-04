package com.fiap.orderhub.orderhub_pedido_service.configuration.feign;

import br.com.orderhub.core.dto.clientes.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "estoque-service", url = "${estoque-service.url}")
public interface EstoqueFeignClient {
    @GetMapping("/estoque/id/{id}")
    ClienteDTO buscarEstoquePorId(@PathVariable("id") Long id);
}
