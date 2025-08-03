package com.fiap.orderhub.orderhub_pedido_service.configuration.feign;

import br.com.orderhub.core.dto.produtos.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produto-service", url = "${produto.service.url}")
public interface ProdutoFeignClient {
    @GetMapping("/produtos/{id}")
    ProdutoDTO buscarProdutoPorId(@PathVariable("id") Long id);

    @GetMapping("/produtos/nome/{nome}")
    ProdutoDTO buscarProdutoPorNome(@PathVariable("nome") String nome);
}