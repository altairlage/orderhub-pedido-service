package com.fiap.orderhub.orderhub_pedido_service.adapters.api.handler;

import br.com.orderhub.core.exceptions.*;
import com.fiap.orderhub.orderhub_pedido_service.adapters.api.handler.OrderhubExceptionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class OrderhubExceptionHandlerTest {

    private final OrderhubExceptionHandler handler = new OrderhubExceptionHandler();

    @Test
    void deveRetornarNotFound_quandoEstoqueNaoEncontradoExceptionException() {
        EstoqueNaoEncontradoException ex = new EstoqueNaoEncontradoException("Estoque não encontrado");

        ResponseEntity<String> response = handler.handleOrderhubException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Estoque não encontrado", response.getBody());
    }

    @Test
    void deveRetornarBadRequest_quandoOrderhubExceptionGenerica() {
        OrderhubException ex = new OrderhubException("Erro genérico");

        ResponseEntity<String> response = handler.handleOrderhubException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro genérico", response.getBody());
    }

    @Test
    void deveRetornarBadRequest_quandoIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("Argumento inválido");

        ResponseEntity<String> response = handler.handleIllegalArgumentException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Argumento inválido", response.getBody());
    }

    @Test
    void deveRetornarInternalServerError_quandoExceptionGenerica() {
        Exception ex = new Exception("Falha inesperada");

        ResponseEntity<String> response = handler.handleGeneralError(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno: Falha inesperada", response.getBody());
    }
}