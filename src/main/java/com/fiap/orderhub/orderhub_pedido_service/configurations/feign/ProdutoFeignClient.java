package com.fiap.orderhub.orderhub_pedido_service.configurations.feign;

import com.fiap.orderhub.orderhub_pedido_service.dto.ProdutoApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produto-service", url = "${produto.service.url}")
public interface ProdutoFeignClient {
    @GetMapping("/produto/id/{id}")
    ProdutoApiResponseDto buscarProdutoPorId(@PathVariable("id") Long id);
}
