package com.fiap.orderhub.orderhub_pedido_service.mapper;

import br.com.orderhub.core.domain.entities.Pedido;
import br.com.orderhub.core.domain.enums.StatusPedido;
import com.fiap.orderhub.orderhub_pedido_service.persistence.ItemPedidoEntity;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PedidoEntityMapper {
    public static List<Map<String, Object>> itemEntityListToMapList(List<ItemPedidoEntity> itens) {
        return itens.stream().map(item -> {
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("idProduto", item.getIdProduto());
            map.put("quantidade", item.getQuantidade());
            return map;
        }).toList();
    }

    public static List<ItemPedidoEntity> mapListToItemEntityList(List<Map<String, Object>> itens) {
        return itens.stream().map(map -> {
            ItemPedidoEntity entity = new ItemPedidoEntity();
            entity.setIdProduto((Long) map.get("idProduto"));
            entity.setQuantidade((Integer) map.get("quantidade"));
            return entity;
        }).toList();
    }

    public static List<Pedido> mapListToPedidoList(List<PedidoEntity> itens) {
        return itens.stream().map(map -> {
            Pedido entity = new Pedido(map.getIdPedido(),
                    map.getIdCliente(),
                    map.getIdPagamento(),
                    itemEntityListToMapList(map.getListaQtdProdutos()),
                    map.getStatus());
            return entity;
        }).toList();
    }

    public static Pedido entityToDomain(PedidoEntity pedidoEntity) {
         return new Pedido(
             pedidoEntity.getIdPedido(),
             pedidoEntity.getIdCliente(),
             pedidoEntity.getIdPagamento(),
             itemEntityListToMapList(pedidoEntity.getListaQtdProdutos()),
             StatusPedido.valueOf(String.valueOf(pedidoEntity.getStatus()))
         );
     }

     public static PedidoEntity domainToEntity(Pedido pedido) {
         return new PedidoEntity(
             pedido.getIdPedido(),
             pedido.getIdCliente(),
             pedido.getIdPagamento(),
             mapListToItemEntityList(pedido.getListaQtdProdutos()),
             pedido.getStatus()
         );
     }
}
