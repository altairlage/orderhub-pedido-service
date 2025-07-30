package com.fiap.orderhub.orderhub_pedido_service.controller;

import br.com.orderhub.core.controller.ProdutoController;
import br.com.orderhub.core.dto.produtos.ProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoApiController {
    private final ProdutoController produtoController;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable Long id) {
        ProdutoDTO produto = produtoController.buscarProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }
}
