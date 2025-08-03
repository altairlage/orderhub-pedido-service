package com.fiap.orderhub.orderhub_pedido_service.gateway.impl;

import br.com.orderhub.core.domain.entities.Cliente;
import br.com.orderhub.core.domain.presenters.ClientePresenter;
import br.com.orderhub.core.exceptions.ClienteNaoEncontradoException;
import br.com.orderhub.core.exceptions.OrderhubException;
import br.com.orderhub.core.interfaces.IClienteGateway;
import com.fiap.orderhub.orderhub_pedido_service.configuration.feign.ClienteFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteGatewayImpl implements IClienteGateway {
    private final ClienteFeignClient clienteFeignClient;

    @Override
    public Cliente buscarPorId(Long id) {
        try {
            var dto = clienteFeignClient.buscarClientePorId(id);
            return ClientePresenter.ToDomain(dto);
        } catch (FeignException.NotFound e) {
            log.error("Erro ao buscar cliente por ID: {}", id, e);
            throw new ClienteNaoEncontradoException(e.getMessage());
        } catch (RuntimeException e) {
            log.error("Erro ao solicitar cliente {}", id, e);
            throw new OrderhubException("Erro ao buscar cliente: " + id + e.getMessage());
        }
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
