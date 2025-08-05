package com.fiap.orderhub.orderhub_pedido_service.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "itens_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private PedidoEntity pedido;

    @Column(nullable = false)
    private Long idProduto;

    @Column(nullable = false)
    private Integer quantidade;

    public ItemPedidoEntity(PedidoEntity pedido, Long idProduto, Integer quantidade) {
        this.pedido = pedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }
}
