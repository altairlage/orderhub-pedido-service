package com.fiap.orderhub.orderhub_pedido_service.persistence;

import br.com.orderhub.core.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pagamento_id")
    private PagamentoEntity pagamento;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedidoEntity> listaQtdProdutos;

    private StatusPedido status = null;
}
