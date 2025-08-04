package com.fiap.orderhub.orderhub_pedido_service.controller;

import br.com.orderhub.core.domain.entities.Produto;
import com.fiap.orderhub.orderhub_pedido_service.gateway.impl.ProdutoGatewayImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos/produtos")
@AllArgsConstructor
public class ProdutoApiController {

    private final ProdutoGatewayImpl produtoGateway;

    @GetMapping("/{id}")
    public Produto buscarProdutoPorId(@PathVariable Long id) {
        return produtoGateway.buscarPorId(id);
    }
}