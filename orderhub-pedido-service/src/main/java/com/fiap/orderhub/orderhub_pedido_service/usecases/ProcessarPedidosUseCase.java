package com.fiap.orderhub.orderhub_pedido_service.usecases;

import br.com.orderhub.core.controller.EstoqueController;
import br.com.orderhub.core.controller.PagamentoController;
import br.com.orderhub.core.controller.PedidoController;
import br.com.orderhub.core.domain.entities.*;
import br.com.orderhub.core.domain.enums.StatusPagamento;
import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.domain.presenters.ClientePresenter;
import br.com.orderhub.core.domain.presenters.PagamentoPresenter;
import br.com.orderhub.core.domain.presenters.PedidoPresenter;
import br.com.orderhub.core.dto.clientes.ClienteDTO;
import br.com.orderhub.core.dto.pagamentos.CriarPagamentoDTO;
import br.com.orderhub.core.dto.pagamentos.PagamentoDTO;
import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;

import br.com.orderhub.core.dto.pedidos.PedidoDTO;
import br.com.orderhub.core.interfaces.IClienteGateway;
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

    private final PedidoController pedidoController;
    private final EstoqueController estoqueController;
    private final PagamentoController pagamentoController;
    private final IPedidoGateway pedidoGateway;
    private final IClienteGateway clienteGateway;

    public void executar(CriarPedidoDTO dto) throws Exception {
        try {
            Cliente cliente = clienteGateway.buscarPorId(dto.idCliente());
            ClienteDTO clienteDTO = ClientePresenter.ToDTO(cliente);

            PedidoDTO pedidoDTO = pedidoController.criarPedido(dto);
            Pedido pedido = PedidoPresenter.ToDomain(pedidoDTO);

            pedido.getListaQtdProdutos().forEach(item -> {
                Integer quantidade = (Integer) item.get("quantidade");
                Long idProduto = (Long) item.get("idProduto");
                estoqueController.baixar(idProduto, quantidade);
            });

            CriarPagamentoDTO criarPagamentoDTO = new CriarPagamentoDTO(
                    clienteDTO.nome(),
                    clienteDTO.email(),
                    pedido.calcularValorTotal(),
                    StatusPagamento.EM_ABERTO
            );

            PagamentoDTO pagamentoDTO = pagamentoController.gerarOrdemPagamento(criarPagamentoDTO);
            Pagamento pagamento = PagamentoPresenter.ToDomain(pagamentoDTO);
            pedido.setPagamento(pagamento);
            pedido.setStatus(StatusPedido.ABERTO);
            pedidoGateway.criar(pedido);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar pedido: " + e.getMessage());
        }

    }
}
