package com.fiap.orderhub.orderhub_pedido_service.gateway.impl;

import br.com.orderhub.core.domain.entities.Produto;
import br.com.orderhub.core.interfaces.IProdutoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoGatewayImpl implements IProdutoGateway {
    @Override
    public Produto buscarPorId(Long id) {
        return null;
    }

    @Override
    public Produto buscarPorNome(String nome) {
        return null;
    }

    @Override
    public Produto criar(Produto produto) {
        return null;
    }

    @Override
    public Produto atualizar(Produto produto) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }

    @Override
    public List<Produto> listarTodos() {
        return List.of();
    }
}
