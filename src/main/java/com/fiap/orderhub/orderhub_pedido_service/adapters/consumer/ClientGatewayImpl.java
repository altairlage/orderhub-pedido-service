package com.fiap.orderhub.orderhub_pedido_service.adapters.consumer;

import br.com.orderhub.core.domain.entities.Cliente;
import br.com.orderhub.core.interfaces.IClienteGateway;
import com.fiap.orderhub.orderhub_pedido_service.configurations.feign.ClienteFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClientGatewayImpl implements IClienteGateway {
    private final ClienteFeignClient clienteFeignClient;

    @Autowired
    public ClientGatewayImpl(ClienteFeignClient clienteFeignClient) {
        this.clienteFeignClient = clienteFeignClient;
    }

    @Override
    public Cliente buscarPorId(Long id) {
        var dto = clienteFeignClient.buscarClientePorId(id);
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

    @Override
    public Cliente buscarPorCpf(String cpf) {
        // Implementação futura
        return null;
    }

    @Override
    public Cliente buscarPorNome(String nome) {
        // Implementação futura
        return null;
    }

    @Override
    public Cliente criar(Cliente cliente) {
        // Implementação futura
        return null;
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        // Implementação futura
        return null;
    }

    @Override
    public void remover(Long id) {
        // Implementação futura
    }

    @Override
    public List<Cliente> listarTodos() {
        // Implementação futura
        return Collections.emptyList();
    }
}
