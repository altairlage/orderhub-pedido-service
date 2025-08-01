package com.fiap.orderhub.orderhub_pedido_service.adapters.consumer;

import br.com.orderhub.core.domain.entities.Cliente;
import br.com.orderhub.core.interfaces.IClienteGateway;
import java.util.List;

public class ClientGatewayBase implements IClienteGateway {
    @Override
    public Cliente buscarPorId(Long id) {
        throw new UnsupportedOperationException("Método não implementado");
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        throw new UnsupportedOperationException("Método não implementado");
    }

    @Override
    public Cliente buscarPorNome(String nome) {
        throw new UnsupportedOperationException("Método não implementado");
    }

    @Override
    public Cliente criar(Cliente cliente) {
        throw new UnsupportedOperationException("Método não implementado");
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        throw new UnsupportedOperationException("Método não implementado");
    }

    @Override
    public void remover(Long id) {
        throw new UnsupportedOperationException("Método não implementado");
    }

    @Override
    public List<Cliente> listarTodos() {
        throw new UnsupportedOperationException("Método não implementado");
    }

}
