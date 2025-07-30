package com.fiap.orderhub.orderhub_pedido_service.usecases;

import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import com.fiap.orderhub.orderhub_pedido_service.configurations.ClienteClient;
import com.fiap.orderhub.orderhub_pedido_service.configurations.EstoqueClient;
import com.fiap.orderhub.orderhub_pedido_service.configurations.PagamentoClient;
import com.fiap.orderhub.orderhub_pedido_service.configurations.ProdutoClient;
import com.fiap.orderhub.orderhub_pedido_service.domain.ItemPedido;
import com.fiap.orderhub.orderhub_pedido_service.domain.Pedido;
import com.fiap.orderhub.orderhub_pedido_service.domain.PedidoRepository;
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

    private final PedidoRepository pedidoRepository;
    private final ProdutoClient produtoClient;
    private final ClienteClient clienteClient;
    private final EstoqueClient estoqueClient;
    private final PagamentoClient pagamentoClient;

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

    public void run(CriarPedidoDTO pedidoDTO) {
        // Extrai os ids dos produtos a partir das chaves dos maps
        List<Long> idsProdutos = pedidoDTO.listaQtdProdutos().stream()
                .flatMap(map -> map.keySet().stream().map(Integer::longValue))
                .collect(Collectors.toList());

        // Busca os produtos
        List<Produto> produtos = produtoClient.buscarProdutos(idsProdutos);

        // Cria os itens do pedido, cada produto com quantidade 1
        List<ItemPedido> itens = produtos.stream().map(produto ->
                new ItemPedido(BigDecimal.valueOf(produto.getId()), 1)
        ).collect(Collectors.toList());

        // Cria o pedido
        Pedido pedido = new Pedido(pedidoDTO.clienteDTO().id(), itens);

        // Atualiza o estoque
        estoqueClient.reporEstoque(itens);

        // Efetua o pagamento
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