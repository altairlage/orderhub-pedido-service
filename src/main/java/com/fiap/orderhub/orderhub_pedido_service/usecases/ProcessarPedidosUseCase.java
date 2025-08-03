package com.fiap.orderhub.orderhub_pedido_service.usecases;

import br.com.orderhub.core.domain.entities.Cliente;
import br.com.orderhub.core.domain.entities.Estoque;
import br.com.orderhub.core.domain.entities.Pedido;
import br.com.orderhub.core.domain.entities.Produto;
import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.dto.clientes.ClienteDTO;
import br.com.orderhub.core.dto.pedidos.PedidoDTO;
import br.com.orderhub.core.domain.usecases.clientes.BuscarClientePorId;
import br.com.orderhub.core.exceptions.ClienteNaoEncontradoException;
import br.com.orderhub.core.exceptions.ProdutoNaoEncontradoException;
import br.com.orderhub.core.interfaces.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
public class ProcessarPedidosUseCase {

    private final IProdutoGateway produtoGateway;
    private final IClienteGateway clienteGateway;
    private final IEstoqueGateway estoqueGateway;
    private final IPagamentoGateway pagamentoGateway;
    private final IPedidoGateway pedidoGateway;

    public void executar(PedidoDTO dto) {
        List<Produto> produtos = recuperaProdutos(dto.listaQtdProdutos());
        Cliente clientes = recuperaCliente(dto.cliente());
        //Service de estoque - dar baixa no estoque passando a lista de IDs dos produtos
        recuperaEstoque(produtos.stream().map(Produto::getId).toList());
        //Service de pagamento - gerar ordem de pagamento com o valor total do pedido
        //Service de pedido - salvar pedido com status de AGUARDANDO PAGAMENTO e salva no banco de dados
    }
    private void recuperaEstoque(List<Long> idsProdutos) {
        Estoque estoque = estoqueGateway.buscarPorIds(idsProdutos);
        if (estoque == null || estoque.getProdutos().isEmpty()) {
            throw new ProdutoNaoEncontradoException("Nenhum produto encontrado no estoque para os IDs fornecidos.");
        }
    }

    private List<Produto> recuperaProdutos(List<Map<String, Object>> listaQtdProdutos) {
        List<String> nomes = listaQtdProdutos.stream()
                .flatMap(item -> item.keySet().stream())
                .toList();

        return nomes.stream().map(nome -> {
            return produtoGateway.buscarPorNome(nome);
        }).toList();
    }

    private Cliente recuperaCliente(ClienteDTO dto) {
        return clienteGateway.buscarPorId(dto.id());
    }
}
