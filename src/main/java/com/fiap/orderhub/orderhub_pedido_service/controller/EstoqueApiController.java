package com.fiap.orderhub.orderhub_pedido_service.controller;

import com.fiap.orderhub.orderhub_pedido_service.dto.EstoqueApiDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoques")
@RequiredArgsConstructor
public class EstoqueApiController {
    private final EstoqueController estoqueController;

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<EstoqueApiDTO> buscarEstoquePorProdutoId(@PathVariable Long produtoId) {
        EstoqueDTO estoque = estoqueController.buscarEstoquePorSku(sku);
        EstoqueApiDTO responseDto = EstoqueApiDtoMapper.estoqueDtoToResponseDto(estoqueDTO);
        return ResponseEntity.ok(estoque);
    }
}