package com.fiap.orderhub.orderhub_pedido_service.usecases;

import com.fiap.orderhub.orderhub_pedido_service.domain.ItemPedido;
import com.fiap.orderhub.orderhub_pedido_service.domain.Pedido;
import com.fiap.orderhub.orderhub_pedido_service.domain.Produto;
import com.fiap.orderhub.orderhub_pedido_service.dto.PedidoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
public class ProcessarPedidosUseCase {

    public void executar(PedidoRequestDTO dto) {

        List<Produto> produtos = produtoClient.buscarProdutos(dto.getIdsProdutos());
        List<ItemPedido> itens = produtos.stream()
                .map(produto -> new ItemPedido(
                        BigDecimal.valueOf(produto.getId()),
                        produto.getQuantidade()
                ))
                .collect(Collectors.toList());

        var pedido = new Pedido(dto.getIdCliente(), itens);

        estoqueClient.reporEstoque(itens);

        boolean sucesso = pagamentoClient.efetuarPagamento(pedido);

        if (sucesso) {
            pedido.confirmar();
        } else {
            pedido.falhar();
            estoqueClient.reporEstoque(itens);
        }

        pedidoRepository.salvar(pedido);
    }
}