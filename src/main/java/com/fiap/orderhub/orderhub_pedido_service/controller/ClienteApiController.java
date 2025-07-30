package com.fiap.orderhub.orderhub_pedido_service.controller;

import br.com.orderhub.core.controller.ClienteController;
import br.com.orderhub.core.dto.clientes.ClienteDTO;
import com.fiap.orderhub.orderhub_pedido_service.dto.ClienteApiDTO;
import com.fiap.orderhub.orderhub_pedido_service.mapper.ClienteApiDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteApiController {
    private final ClienteController clienteController;

    @GetMapping("/id/{id}")
    public ResponseEntity<ClienteApiDTO> buscarClientePorId(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteController.buscarClientePorId(id);
        ClienteApiDTO responseDto = ClienteApiDtoMapper.clienteDtoToResponseDto(clienteDTO);
        return ResponseEntity.ok(responseDto);
    }
}
