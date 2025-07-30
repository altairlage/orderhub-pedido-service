package com.fiap.orderhub.orderhub_pedido_service.dto;

import java.time.LocalDateTime;

public record EstoqueApiDTO (
        String sku,
        Integer quantidadeDisponivel,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {

}
