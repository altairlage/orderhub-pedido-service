package com.fiap.orderhub.orderhub_pedido_service.dto;

import java.time.LocalDateTime;

public record EstoqueApiResponseDto(
        Long id,
        Integer quantidadeDisponivel,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {}
