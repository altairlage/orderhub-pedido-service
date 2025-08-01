package com.fiap.orderhub.orderhub_pedido_service.exceptions;

public class BuscarProdutoException extends RuntimeException {
    public BuscarProdutoException(String message) {
        super(message);
    }

    public BuscarProdutoException(String message, Throwable cause) {
        super(message, cause);
    }
}

