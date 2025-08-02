package com.fiap.orderhub.orderhub_pedido_service.mapper;

import br.com.orderhub.core.domain.entities.Produto;
import br.com.orderhub.core.dto.produtos.ProdutoDTO;
import com.fiap.orderhub.orderhub_pedido_service.dto.ProdutoApiResponseDto;

public class ProdutoMapper {
    public static Produto toEntity(ProdutoApiResponseDto dto) {
        if (dto == null) return null;
        return new Produto(
            dto.getId(),
            dto.getNome(),
            dto.getDescricao(),
            dto.getPreco()
        );
    }

    public static ProdutoDTO toDto(Produto produto) {
        if (produto == null) return null;
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco()
        );
    }

    public static Produto toEntity(ProdutoDTO dto) {
        if (dto == null) return null;
        return new Produto(
            dto.id(),
            dto.nome(),
            dto.descricao(),
            dto.preco()
        );
    }
}
