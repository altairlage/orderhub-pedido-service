package com.fiap.orderhub.orderhub_pedido_service.gateway.impl;

import br.com.orderhub.core.domain.entities.Pedido;
import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.interfaces.IPedidoGateway;
import com.fiap.orderhub.orderhub_pedido_service.mapper.PedidoEntityMapper;
import com.fiap.orderhub.orderhub_pedido_service.persistence.ItemPedidoEntity;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoEntity;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoGatewayImpl implements IPedidoGateway {

    private final PedidoRepository pedidoRepository;

    @Override
    public Pedido buscarPorId(Long idPedido) {
        Optional<PedidoEntity> optionalPedido = pedidoRepository.findById(idPedido.toString());
        if(optionalPedido.isEmpty()) {
            return null;
        }

        PedidoEntity pedidoEntity = optionalPedido.get();

        return optionalPedido.map(PedidoEntityMapper::entityToDomain).orElse(null);
    }

    @Override
    public List<Pedido> buscarPorIdCliente(Long idCliente) {
        List<PedidoEntity> pedidos = pedidoRepository.findByIdCliente(idCliente);
        return PedidoEntityMapper.mapListToPedidoList(pedidos);
    }

    @Override
    public Pedido criar(Pedido pedido) {
        PedidoEntity pedidoEntity = PedidoEntityMapper.domainToEntity(pedido);
        PedidoEntity savedEntity = pedidoRepository.save(pedidoEntity);
        return PedidoEntityMapper.entityToDomainCreation(savedEntity);
    }

    @Override
    public Pedido editar(Pedido pedidoAntigo, Pedido pedidoAtualizado) {
        PedidoEntity entity = pedidoRepository.findById(pedidoAntigo.getIdPedido().toString())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Atualiza os campos
        entity.setIdPagamento(pedidoAtualizado.getIdPagamento());
        entity.setStatus(pedidoAtualizado.getStatus());

        // Constrói nova lista de itens
        List<ItemPedidoEntity> itemPedidoEntities = new ArrayList<>();
        for (Map<String, Object> produto : pedidoAtualizado.getListaQtdProdutos()) {
            Long idProduto = Long.parseLong(produto.get("idProduto").toString());
            Integer quantidade = (Integer) produto.get("quantidade");

            ItemPedidoEntity item = new ItemPedidoEntity();
            item.setIdProduto(idProduto);
            item.setQuantidade(quantidade);
            item.setPedido(entity); // define o vínculo com o pedido

            itemPedidoEntities.add(item);
        }

        entity.setListaQtdProdutos(itemPedidoEntities);

        PedidoEntity savedEntity = pedidoRepository.save(entity);
        return PedidoEntityMapper.entityToDomain(savedEntity);
    }

    @Override
    public Pedido editarStatus(Long idPedido, StatusPedido status) {
        Optional<PedidoEntity> optionalPedido = pedidoRepository.findById(idPedido.toString());
        if (optionalPedido.isEmpty()) {
            return null;
        }
        PedidoEntity pedidoEntity = optionalPedido.get();
        pedidoEntity.setStatus(status);
        PedidoEntity updatedEntity = pedidoRepository.save(pedidoEntity);
        return PedidoEntityMapper.entityToDomain(updatedEntity);
    }

    @Override
    public List<Pedido> listarTodos() {
        List<PedidoEntity> pedidos = pedidoRepository.findAll();

        // ⚠️ A lógica abaixo causaria ConcurrentModificationException
        // Substituído por filtro usando stream
        List<PedidoEntity> abertos = pedidos.stream()
                .filter(p -> p.getStatus() == StatusPedido.ABERTO)
                .toList();

        return PedidoEntityMapper.mapListToPedidoList(abertos);
    }
}
