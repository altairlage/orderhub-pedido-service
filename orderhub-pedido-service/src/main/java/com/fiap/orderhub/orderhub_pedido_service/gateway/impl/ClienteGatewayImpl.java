package com.fiap.orderhub.orderhub_pedido_service.gateway.impl;

import br.com.orderhub.core.domain.entities.Cliente;
import br.com.orderhub.core.domain.presenters.ClientePresenter;
import br.com.orderhub.core.dto.clientes.ClienteDTO;
import br.com.orderhub.core.interfaces.IClienteGateway;
import com.fiap.orderhub.orderhub_pedido_service.configuration.feign.ClienteFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteGatewayImpl implements IClienteGateway {
    @Autowired
    private ClienteFeignClient clienteFeignClient;

    @Override
    public Cliente buscarPorId(Long id) {
        ClienteDTO cliente = clienteFeignClient.buscarClientePorId(id);
        var clienteResponse = ClientePresenter.ToDomain(cliente);
        return clienteResponse;
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
