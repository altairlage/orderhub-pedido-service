package com.fiap.orderhub.orderhub_pedido_service.gateway;

import br.com.orderhub.core.domain.entities.Cliente;
import br.com.orderhub.core.interfaces.IClienteGateway;
import com.fiap.orderhub.orderhub_pedido_service.configurations.feign.ClienteFeignClient;
import com.fiap.orderhub.orderhub_pedido_service.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteGatewayImpl implements IClienteGateway {
    private final ClienteFeignClient clienteFeignClient;

    @Override
    public Cliente buscarPorId(Long id) {
        var dto = clienteFeignClient.buscarClientePorId(id);
        if (dto == null) return null;
        return ClienteMapper.toEntity(dto);
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
