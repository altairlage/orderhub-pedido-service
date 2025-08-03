package com.fiap.orderhub.orderhub_pedido_service.usecases;

import br.com.orderhub.core.domain.entities.*;
import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.domain.presenters.ClientePresenter;
import br.com.orderhub.core.domain.usecases.clientes.BuscarClientePorId;
import br.com.orderhub.core.domain.usecases.estoques.BaixarEstoque;
import br.com.orderhub.core.domain.usecases.pagamentos.GerarOrdemPagamento;
import br.com.orderhub.core.domain.usecases.pedidos.CriarPedido;
import br.com.orderhub.core.dto.clientes.ClienteDTO;
import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;

import br.com.orderhub.core.interfaces.IPedidoGateway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@Service
public class ProcessarPedidosUseCase {

    private final CriarPedido criarPedido;
    private final BuscarClientePorId buscarClientePorId;
    private final BaixarEstoque baixarEstoque;
    private final GerarOrdemPagamento gerarOrdemPagamento;
    private final IPedidoGateway pedidoGateway;

    public void executar(CriarPedidoDTO dto) {
        Pedido pedido = criarPedido.run(dto);
        pedido.getListaQtdProdutos().forEach(item -> {
            Integer quantidade = (Integer) item.get("quantidade");
            Long idProduto = (Long) item.get("idProduto");
            baixarEstoque.executar(idProduto, quantidade);
        });

        ClienteDTO clienteDTO = ClientePresenter.ToDTO(pedido.getCliente());

        Pagamento pagamento = gerarOrdemPagamento.run(clienteDTO);

        pedido.setPagamento(pagamento);
        pedido.setStatus(StatusPedido.ABERTO);
        pedidoGateway.criar(pedido);
    }
}
