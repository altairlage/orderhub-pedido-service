package com.fiap.orderhub.orderhub_pedido_service.mapper;

import br.com.orderhub.core.domain.entities.Cliente;
import br.com.orderhub.core.dto.clientes.ClienteDTO;

public class ClienteMapper {
    public static Cliente toEntity(ClienteDTO dto) {
        return new Cliente(
            dto.id(),
            dto.nome(),
            dto.cpf(),
            dto.dataNascimento(),
            dto.endereco(),
            dto.numeroContato(),
            dto.email(),
            dto.infoPagamento()
        );
    }

    public static ClienteDTO toDto(Cliente cliente) {
        return new ClienteDTO(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getDataNascimento(),
            cliente.getEndereco(),
            cliente.getNumeroContato(),
            cliente.getEmail(),
            cliente.getInfoPagamento()
        );
    }
}
