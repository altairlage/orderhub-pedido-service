package com.fiap.orderhub.orderhub_pedido_service.dto;

public record ClienteApiDTO(
        Long id,
        String nome,
        String cpf,
        String dataNascimento,
        String endereco,
        String numeroContato,
        String email,
        String infoPagamento
) {
}