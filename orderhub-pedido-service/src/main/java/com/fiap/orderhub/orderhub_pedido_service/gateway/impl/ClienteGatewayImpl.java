package com.fiap.orderhub.orderhub_pedido_service.gateway.impl;

import br.com.orderhub.core.domain.entities.Cliente;
import br.com.orderhub.core.interfaces.IClienteGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteGatewayImpl implements IClienteGateway {
    @Override
    public Cliente buscarPorId(Long id) {
        return null;
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        return null;
    }

    @Override
    public Cliente buscarPorNome(String nome) {
        return null;
    }

    @Override
    public Cliente criar(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        return null;
    }

    @Override
    public void remover(Long id) {

    }

    @Override
    public List<Cliente> listarTodos() {
        return List.of();
    }
}
