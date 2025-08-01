package com.fiap.orderhub.orderhub_pedido_service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoApiResponseDto {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;

}
