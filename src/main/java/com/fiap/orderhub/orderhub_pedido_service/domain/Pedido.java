package com.fiap.orderhub.orderhub_pedido_service.domain;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {
    private Long id;
    private Long idCliente;
    private List<ItemPedido> itens;
    private BigDecimal total;
    private StatusPedido status;

    public Pedido(Long idCliente, List<ItemPedido> itens) {
        this.idCliente = idCliente;
        this.itens = itens;
        this.total = calcularTotal();
        this.status = StatusPedido.PENDENTE;
    }

    private BigDecimal calcularTotal() {
        return itens.stream()
                .map(i -> i.getPreco().multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void confirmar() { this.status = StatusPedido.CONFIRMADO; }
    public void falhar() { this.status = StatusPedido.FALHA_PAGAMENTO; }
    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}
