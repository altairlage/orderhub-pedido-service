package com.fiap.orderhub.orderhub_pedido_service.mapper;

import br.com.orderhub.core.domain.entities.Cliente;
import com.fiap.orderhub.orderhub_pedido_service.persistence.ClienteEntity;

public class ClienteEntityMapper {
    public static Cliente entityToDomain(ClienteEntity entity) {
        if (entity == null) return null;
        return new Cliente(
            entity.getId(),
            entity.getNome(),
            entity.getCpf(),
            entity.getDataNascimento(),
            entity.getEndereco(),
            entity.getNumeroContato(),
            entity.getEmail(),
            entity.getInfoPagamento()
        );
    }

    public static ClienteEntity domainToEntity(Cliente cliente) {
        if (cliente == null) return null;
        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId());
        entity.setNome(cliente.getNome());
        entity.setCpf(cliente.getCpf());
        entity.setDataNascimento(cliente.getDataNascimento());
        entity.setEndereco(cliente.getEndereco());
        entity.setNumeroContato(cliente.getNumeroContato());
        entity.setEmail(cliente.getEmail());
        entity.setInfoPagamento(cliente.getInfoPagamento());
        return entity;
    }
}
