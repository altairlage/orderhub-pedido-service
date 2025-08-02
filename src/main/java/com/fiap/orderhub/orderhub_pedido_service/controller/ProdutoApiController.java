package com.fiap.orderhub.orderhub_pedido_service.controller;

import com.fiap.orderhub.orderhub_pedido_service.configurations.feign.ProdutoFeignClient;
import com.fiap.orderhub.orderhub_pedido_service.dto.ProdutoApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoApiController {
    private final ProdutoFeignClient produtoFeignClient;

    @GetMapping("/{id}")
    public ProdutoApiResponseDto buscarProdutoPorId(@PathVariable Long id) {
        return produtoFeignClient.buscarProdutoPorId(id);
    }

}
