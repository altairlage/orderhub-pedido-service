package com.fiap.orderhub.orderhub_pedido_service.mapper;

import br.com.orderhub.core.domain.entities.Pedido;
import br.com.orderhub.core.domain.enums.StatusPedido;
import com.fiap.orderhub.orderhub_pedido_service.persistence.ItemPedidoEntity;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PedidoEntityMapper {

    // Converte lista de entidades de item para lista de mapas
    public static List<Map<String, Object>> itemEntityListToMapList(List<ItemPedidoEntity> itens) {
        return itens.stream().map(item -> {
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("idProduto", item.getIdProduto()); // atualizado
            map.put("quantidade", item.getQuantidade());
            return map;
        }).collect(Collectors.toList());
    }

    // Converte lista de mapas em lista de entidades de item
    public static List<ItemPedidoEntity> mapListToItemEntityList(List<Map<String, Object>> itens) {
        return itens.stream().map(map -> {
            ItemPedidoEntity entity = new ItemPedidoEntity();
            entity.setIdProduto(Long.parseLong(map.get("idProduto").toString()));
            entity.setQuantidade((Integer) map.get("quantidade"));
            return entity;
        }).collect(Collectors.toList());
    }

    // Converte lista de PedidoEntity em lista de Pedido (domínio)
    public static List<Pedido> mapListToPedidoList(List<PedidoEntity> pedidos) {
        return pedidos.stream().map(PedidoEntityMapper::entityToDomain).collect(Collectors.toList());
    }

    // Converte um PedidoEntity em Pedido (domínio)
    public static Pedido entityToDomain(PedidoEntity pedidoEntity) {
        return new Pedido(
                pedidoEntity.getIdPedido(),
                pedidoEntity.getIdCliente(),
                pedidoEntity.getIdPagamento(),
                itemEntityListToMapList(pedidoEntity.getListaQtdProdutos()),
                pedidoEntity.getStatus()
        );
    }

    public static Pedido entityToDomainCreation(PedidoEntity pedidoEntity) {
        return new Pedido(
                pedidoEntity.getIdPedido(),
                pedidoEntity.getIdCliente(),
                itemEntityListToMapList(pedidoEntity.getListaQtdProdutos()),
                pedidoEntity.getStatus()
        );
    }

    // Converte um Pedido (domínio) em PedidoEntity
    public static PedidoEntity domainToEntity(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();
        entity.setIdPedido(pedido.getIdPedido());
        entity.setIdCliente(pedido.getIdCliente());
        entity.setIdPagamento(pedido.getIdPagamento());
        entity.setStatus(pedido.getStatus());

        List<ItemPedidoEntity> itens = mapListToItemEntityList(pedido.getListaQtdProdutos());
        // associar o pedido aos itens, se necessário
        itens.forEach(item -> item.setPedido(entity));

        entity.setListaQtdProdutos(itens);
        return entity;
    }
}
