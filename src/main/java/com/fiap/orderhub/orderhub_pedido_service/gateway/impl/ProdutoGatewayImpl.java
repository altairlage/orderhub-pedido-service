package com.fiap.orderhub.orderhub_pedido_service.gateway.impl;

import br.com.orderhub.core.domain.entities.Produto;
import br.com.orderhub.core.domain.presenters.ProdutoPresenter;
import br.com.orderhub.core.exceptions.OrderhubException;
import br.com.orderhub.core.exceptions.ProdutoNaoEncontradoException;
import br.com.orderhub.core.interfaces.IProdutoGateway;
import com.fiap.orderhub.orderhub_pedido_service.configuration.feign.ProdutoFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutoGatewayImpl implements IProdutoGateway {
    private final ProdutoFeignClient produtoFeignClient;

    @Override
    public Produto buscarPorId(Long id) {
        try {
            var dto = produtoFeignClient.buscarProdutoPorId(id);
            return ProdutoPresenter.ToDomain(dto);
        } catch (FeignException.NotFound e) {
            log.error("Erro ao buscar produto por ID: {}", id, e);
            throw new ProdutoNaoEncontradoException(e.getMessage());
        } catch (RuntimeException e) {
            log.error("Erro ao solicitar produto {}", id, e);
            throw new OrderhubException("Erro ao buscar produto: " + id + e.getMessage());
        }
    }

    @Override
    public Produto buscarPorNome(String nome) {
        try {
            var dto = produtoFeignClient.buscarProdutoPorNome(nome);
            return ProdutoPresenter.ToDomain(dto);
        } catch (FeignException.NotFound e) {
            log.error("Erro ao buscar produto por NOME: {}", nome, e);
            throw new ProdutoNaoEncontradoException(e.getMessage());
        } catch (RuntimeException e) {
            log.error("Erro ao solicitar produto {}", nome, e);
            throw new OrderhubException("Erro ao buscar produto: " + nome + e.getMessage());
        }
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
