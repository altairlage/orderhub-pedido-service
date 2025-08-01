package com.fiap.orderhub.orderhub_pedido_service.controller;

import br.com.orderhub.core.dto.clientes.ClienteDTO;
import com.fiap.orderhub.orderhub_pedido_service.configurations.feign.ClienteFeignClient;
import com.fiap.orderhub.orderhub_pedido_service.dto.ClienteApiResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-feign")
public class TestFeignController {
    private final ClienteFeignClient clienteFeignClient;

    public TestFeignController(ClienteFeignClient clienteFeignClient) {
        this.clienteFeignClient = clienteFeignClient;
    }

    @GetMapping("/{id}")
    public ClienteDTO testFeign(@PathVariable Long id) {
        ClienteApiResponseDto response = clienteFeignClient.buscarClientePorId(id);
        return new ClienteDTO(
            response.getId(),
            response.getNome(),
            response.getCpf(),
            response.getDataNascimento(),
            response.getEndereco(),
            response.getNumeroContato(),
            response.getEmail(),
            response.getInfoPagamento()
        );
    }
}
