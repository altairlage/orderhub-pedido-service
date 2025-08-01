package com.fiap.orderhub.orderhub_pedido_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteApiResponseDto {
    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String endereco;
    private String numeroContato;
    private String email;
    private String infoPagamento;
}
