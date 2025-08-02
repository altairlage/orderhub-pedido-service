package com.fiap.orderhub.orderhub_pedido_service.mapper;

import br.com.orderhub.core.domain.entities.Cliente;
import br.com.orderhub.core.dto.clientes.ClienteDTO;
import com.fiap.orderhub.orderhub_pedido_service.dto.ClienteApiResponseDto;

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

    public static Cliente toEntity(ClienteApiResponseDto dto) {
        if (dto == null) return null;
        return new Cliente(
            dto.getId(),
            dto.getNome(),
            dto.getCpf(),
            dto.getDataNascimento(),
            dto.getEndereco(),
            dto.getNumeroContato(),
            dto.getEmail(),
            dto.getInfoPagamento()
        );
    }
}
