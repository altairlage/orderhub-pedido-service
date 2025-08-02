package com.fiap.orderhub.orderhub_pedido_service.usecases;

import br.com.orderhub.core.domain.entities.Produto;
import br.com.orderhub.core.domain.usecases.produtos.BuscarProdutoPorId;
import br.com.orderhub.core.interfaces.IProdutoGateway;
import com.fiap.orderhub.orderhub_pedido_service.exceptions.BuscarProdutoException;
import com.fiap.orderhub.orderhub_pedido_service.persistence.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarProdutoPorIdUseCaseImpl {
    private final ProdutoRepository produtoRepository;
    private final BuscarProdutoPorId buscarProdutoPorId;

    @Autowired
    public BuscarProdutoPorIdUseCaseImpl(ProdutoRepository produtoRepository, IProdutoGateway produtoGateway) {
        this.produtoRepository = produtoRepository;
        this.buscarProdutoPorId = new BuscarProdutoPorId(produtoGateway);
    }

    public Produto buscarPorId(String id) throws BuscarProdutoException {
        // 1. Tenta buscar localmente
        var produtoEntityOpt = produtoRepository.findById(Long.valueOf(id));
        if (produtoEntityOpt.isPresent()) {
            return produtoEntityOpt.get();
        }

        Produto produto = buscarProdutoPorId.run(Long.valueOf(id));

        // 3. Salva localmente
        produtoRepository.save(produto);

        return produto;
    }
}
