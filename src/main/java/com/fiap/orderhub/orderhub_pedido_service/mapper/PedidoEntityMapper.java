package com.fiap.orderhub.orderhub_pedido_service.mapper;

import br.com.orderhub.core.domain.entities.Pedido;
import br.com.orderhub.core.domain.enums.StatusPedido;
import com.fiap.orderhub.orderhub_pedido_service.persistence.ItemPedidoEntity;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PedidoEntityMapper {
    private static List<Map<String, Object>> itemEntityListToMapList(List<ItemPedidoEntity> itens) {
        return itens.stream().map(item -> {
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("idProduto", item.getIdProduto());
            map.put("quantidade", item.getQuantidade());
            return map;
        }).collect(Collectors.toList());
    }

    private static List<ItemPedidoEntity> mapListToItemEntityList(List<Map<String, Object>> itens) {
        return itens.stream().map(map -> {
            ItemPedidoEntity entity = new ItemPedidoEntity();
            entity.setIdProduto((Long) map.get("idProduto"));
            entity.setQuantidade((Integer) map.get("quantidade"));
            return entity;
        }).collect(Collectors.toList());
    }

    public static Pedido entityToDomain(PedidoEntity pedidoEntity) {
         return new Pedido(
             pedidoEntity.getIdPedido(),
             ClienteEntityMapper.entityToDomain(pedidoEntity.getCliente()),
             PagamentoEntityMapper.entityToDomain(pedidoEntity.getPagamento()),
             itemEntityListToMapList(pedidoEntity.getListaQtdProdutos()),
             StatusPedido.valueOf(String.valueOf(pedidoEntity.getStatus()))
         );
     }

     public static PedidoEntity domainToEntity(Pedido pedido) {
         return new PedidoEntity(
             pedido.getIdPedido(),
             ClienteEntityMapper.domainToEntity(pedido.getCliente()),
             PagamentoEntityMapper.domainToEntity(pedido.getPagamento()),
             mapListToItemEntityList(pedido.getListaQtdProdutos()),
             pedido.getStatus()
         );
     }
}
