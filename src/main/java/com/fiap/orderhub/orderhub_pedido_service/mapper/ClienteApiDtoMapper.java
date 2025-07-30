package com.fiap.orderhub.orderhub_pedido_service.mapper;

import br.com.orderhub.core.dto.clientes.ClienteDTO;
import br.com.orderhub.core.dto.clientes.CriarClienteDTO;
import com.fiap.orderhub.orderhub_pedido_service.dto.ClienteApiDTO;

public class ClienteApiDtoMapper {
    public static ClienteApiDTO clienteDtoToResponseDto(ClienteDTO clienteDTO) {
        return new ClienteApiDTO(
                clienteDTO.id(),
                clienteDTO.nome(),
                clienteDTO.cpf(),
                clienteDTO.dataNascimento(),
                clienteDTO.endereco(),
                clienteDTO.numeroContato(),
                clienteDTO.email(),
                clienteDTO.infoPagamento()
        );
    }

    public static CriarClienteDTO requestDtoToCriarClienteDto(ClienteApiDTO requestDTO) {
        return new CriarClienteDTO(
                requestDTO.nome(),
                requestDTO.cpf(),
                requestDTO.dataNascimento(),
                requestDTO.endereco(),
                requestDTO.numeroContato(),
                requestDTO.email(),
                requestDTO.infoPagamento()
        );
    }

    public static ClienteDTO requestDtoToClienteDto(Long id, ClienteApiDTO requestDto) {
        return new ClienteDTO(
                id,
                requestDto.nome(),
                requestDto.cpf(),
                requestDto.dataNascimento(),
                requestDto.endereco(),
                requestDto.numeroContato(),
                requestDto.email(),
                requestDto.infoPagamento()
        );
    }
}
