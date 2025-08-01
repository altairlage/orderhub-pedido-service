package com.fiap.orderhub.orderhub_pedido_service.configurations;

import com.fiap.orderhub.orderhub_pedido_service.domain.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProdutoClient {
    public List<Produto> buscarProdutos(List<Long> idsProdutos) {
        return new ArrayList<>();
    }
}
