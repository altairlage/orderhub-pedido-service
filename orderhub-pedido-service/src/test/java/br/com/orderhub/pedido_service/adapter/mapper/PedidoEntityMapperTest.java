package br.com.orderhub.pedido_service.adapter.mapper;

import com.fiap.orderhub.orderhub_pedido_service.mapper.PedidoEntityMapper;
import com.fiap.orderhub.orderhub_pedido_service.persistence.ItemPedidoEntity;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoEntity;
import br.com.orderhub.core.domain.entities.Pedido;
import br.com.orderhub.core.domain.enums.StatusPedido;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoEntityMapperTest {

    @Test
    void testEntityToDomainAndBack() {
        PedidoEntity entity = new PedidoEntity();
        entity.setIdPedido(1L);
        entity.setIdCliente(2L);
        entity.setIdPagamento(3L);
        entity.setStatus(StatusPedido.ABERTO);

        Pedido domain = PedidoEntityMapper.entityToDomain(entity);
        assertEquals(entity.getIdPedido(), domain.getIdPedido());
        assertEquals(entity.getIdCliente(), domain.getIdCliente());
        assertEquals(entity.getIdPagamento(), domain.getIdPagamento());
        assertEquals(entity.getStatus(), domain.getStatus());

        PedidoEntity mappedEntity = PedidoEntityMapper.domainToEntity(domain);
        assertEquals(domain.getIdPedido(), mappedEntity.getIdPedido());
    }

    @Test
    void testItemEntityListToMapListAndBack() {
        ItemPedidoEntity item = new ItemPedidoEntity();
        item.setIdProduto(10L);
        item.setQuantidade(5);

        List<Map<String, Object>> maps = PedidoEntityMapper.itemEntityListToMapList(List.of(item));
        assertEquals(1, maps.size());
        assertEquals(10L, maps.get(0).get("idProduto"));
        assertEquals(5, maps.get(0).get("quantidade"));

        List<ItemPedidoEntity> items = PedidoEntityMapper.mapListToItemEntityList(maps);
        assertEquals(1, items.size());
        assertEquals(10L, items.get(0).getIdProduto());
        assertEquals(5, items.get(0).getQuantidade());
    }
}