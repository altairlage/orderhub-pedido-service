package com.fiap.orderhub.orderhub_pedido_service.adapters.consumer;

import br.com.orderhub.core.domain.entities.Produto;
import br.com.orderhub.core.interfaces.IProdutoGateway;
import com.fiap.orderhub.orderhub_pedido_service.configurations.feign.ProdutoFeignClient;
import com.fiap.orderhub.orderhub_pedido_service.mapper.ProdutoMapper;

import java.util.List;

public class ProdutoGatewayImpl implements IProdutoGateway {

    private final ProdutoFeignClient produtoFeignClient;

    public ProdutoGatewayImpl(ProdutoFeignClient produtoFeignClient) {
        this.produtoFeignClient = produtoFeignClient;
    }

    @Override
    public Produto buscarPorId(Long id) {
        var dto = produtoFeignClient.buscarProdutoPorId(id);
        if (dto == null) return null;
        return ProdutoMapper.toEntity(dto);
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
