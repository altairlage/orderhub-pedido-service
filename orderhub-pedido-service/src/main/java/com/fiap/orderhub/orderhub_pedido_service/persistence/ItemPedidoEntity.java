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
    @Id
    private Long idProduto;
    private Integer quantidade;
}
