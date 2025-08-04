package com.fiap.orderhub.orderhub_pedido_service.gateway.impl;

import br.com.orderhub.core.domain.entities.Produto;
import br.com.orderhub.core.domain.presenters.ProdutoPresenter;
import br.com.orderhub.core.dto.produtos.ProdutoDTO;
import br.com.orderhub.core.interfaces.IProdutoGateway;
import com.fiap.orderhub.orderhub_pedido_service.configuration.feign.ProdutoFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoGatewayImpl implements IProdutoGateway {
    @Autowired
    private ProdutoFeignClient produtoFeignClient;

    @Override
    public Produto buscarPorId(Long id) {
        ProdutoDTO produto = produtoFeignClient.buscarProdutoPorId(id);
        var produtoResponse = ProdutoPresenter.ToDomain(produto);
        return produtoResponse;
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
