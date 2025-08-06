package com.fiap.orderhub.orderhub_pedido_service.adapters.mapper;

import br.com.orderhub.core.domain.entities.Pedido;
import br.com.orderhub.core.domain.enums.StatusPedido;
import com.fiap.orderhub.orderhub_pedido_service.mapper.PedidoEntityMapper;
import com.fiap.orderhub.orderhub_pedido_service.persistence.ItemPedidoEntity;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoEntity;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PedidoEntityMapperTest {

    @Test
    void deveConverterItemEntityParaMap() {
        ItemPedidoEntity item = new ItemPedidoEntity();
        item.setIdProduto(10L);
        item.setQuantidade(2);

        List<ItemPedidoEntity> itens = List.of(item);

        List<Map<String, Object>> result = PedidoEntityMapper.itemEntityListToMapList(itens);

        assertEquals(1, result.size());
        assertEquals(10L, result.getFirst().get("idProduto"));
        assertEquals(2, result.getFirst().get("quantidade"));
    }

    @Test
    void deveConverterMapParaItemEntity() {
        Map<String, Object> map = new HashMap<>();
        map.put("idProduto", "20");
        map.put("quantidade", 5);

        List<Map<String, Object>> maps = List.of(map);

        List<ItemPedidoEntity> result = PedidoEntityMapper.mapListToItemEntityList(maps);

        assertEquals(1, result.size());
        assertEquals(20L, result.getFirst().getIdProduto());
        assertEquals(5, result.getFirst().getQuantidade());
    }

    @Test
    void deveConverterPedidoEntityParaPedidoDomain() {
        ItemPedidoEntity item = new ItemPedidoEntity();
        item.setIdProduto(1L);
        item.setQuantidade(3);

        PedidoEntity entity = new PedidoEntity(
                1L,
                100L,
                200L,
                List.of(item),
                StatusPedido.ABERTO
        );

        Pedido result = PedidoEntityMapper.entityToDomain(entity);

        assertEquals(1L, result.getIdPedido());
        assertEquals(100L, result.getIdCliente());
        assertEquals(200L, result.getIdPagamento());
        assertEquals(StatusPedido.ABERTO, result.getStatus());
        assertEquals(1L, result.getListaQtdProdutos().getFirst().get("idProduto"));
    }

    @Test
    void deveConverterPedidoDomainParaPedidoEntity() {
        Map<String, Object> produto = new HashMap<>();
        produto.put("idProduto", "5");
        produto.put("quantidade", 2);

        Pedido pedido = new Pedido(
                1L,
                100L,
                200L,
                List.of(produto),
                StatusPedido.ABERTO
        );


        PedidoEntity entity = PedidoEntityMapper.domainToEntity(pedido);

        assertEquals(1L, entity.getIdPedido());
        assertEquals(100L, entity.getIdCliente());
        assertEquals(200L, entity.getIdPagamento());
        assertEquals(StatusPedido.ABERTO, entity.getStatus());
        assertEquals(5L, entity.getListaQtdProdutos().getFirst().getIdProduto());
        assertEquals(2, entity.getListaQtdProdutos().getFirst().getQuantidade());
        assertEquals(entity, entity.getListaQtdProdutos().getFirst().getPedido());
    }
}

