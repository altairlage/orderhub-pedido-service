package com.fiap.orderhub.orderhub_pedido_service.persistence;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "itens_pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoEntity {

    @EmbeddedId
    private ItemPedidoId id;

    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

}
