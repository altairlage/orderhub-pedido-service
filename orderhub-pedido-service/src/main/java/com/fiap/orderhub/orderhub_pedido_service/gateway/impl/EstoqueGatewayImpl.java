package com.fiap.orderhub.orderhub_pedido_service.gateway.impl;

import br.com.orderhub.core.domain.entities.Estoque;
import br.com.orderhub.core.interfaces.IEstoqueGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EstoqueGatewayImpl implements IEstoqueGateway {
    @Override
    public Optional<Estoque> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Estoque> buscarPorIds(List<Long> ids) {
        return List.of();
    }

    @Override
    public void salvar(Estoque estoque) {

    }
}
