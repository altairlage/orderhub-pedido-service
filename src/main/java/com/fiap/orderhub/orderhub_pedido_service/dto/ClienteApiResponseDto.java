package com.fiap.orderhub.orderhub_pedido_service.dto;

public class ClienteApiResponseDto {
    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String endereco;
    private String numeroContato;
    private String email;
    private String infoPagamento;

    public ClienteApiResponseDto() {}

    public ClienteApiResponseDto(Long id, String nome, String cpf, String dataNascimento, String endereco, String numeroContato, String email, String infoPagamento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.numeroContato = numeroContato;
        this.email = email;
        this.infoPagamento = infoPagamento;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getNumeroContato() { return numeroContato; }
    public void setNumeroContato(String numeroContato) { this.numeroContato = numeroContato; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getInfoPagamento() { return infoPagamento; }
    public void setInfoPagamento(String infoPagamento) { this.infoPagamento = infoPagamento; }
}

