package com.fiap.orderhub.orderhub_pedido_service.service;

import br.com.orderhub.core.controller.PedidoController;
import br.com.orderhub.core.domain.enums.StatusPagamento;
import br.com.orderhub.core.dto.pagamentos.PagamentoDTO;
import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import br.com.orderhub.core.dto.pedidos.PedidoDTO;
import br.com.orderhub.core.dto.pagamentos.CriarPagamentoDTO;
import br.com.orderhub.core.exceptions.ClienteNaoEncontradoException;
import br.com.orderhub.core.exceptions.EstoqueInsuficienteException;
import br.com.orderhub.core.exceptions.OrdemPagamentoNaoEncontradaException;
import br.com.orderhub.core.exceptions.ProdutoNaoEncontradoException;
import com.fiap.orderhub.orderhub_pedido_service.dto.ClienteApiResponseDto;
import com.fiap.orderhub.orderhub_pedido_service.dto.EstoqueApiRequestDto;
import com.fiap.orderhub.orderhub_pedido_service.dto.EstoqueApiResponseDto;
import com.fiap.orderhub.orderhub_pedido_service.dto.ProdutoApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Slf4j
@Service
public class OrquestradorCriacaoPedido {
    private final PedidoController pedidoController;

    @Value("${estoque.service.url}")
    private String estoqueServiceUrl;

    @Value("${cliente.service.url}")
    private String clienteServiceUrl;

    @Value("${pagamento.service.url}")
    private String pagamentoServiceUrl;

    @Value("${produto.service.url}")
    private String produtoServiceUrl;

    public OrquestradorCriacaoPedido(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
    }

    public void criarPedido(CriarPedidoDTO criarPedidoDTO) {
        ClienteApiResponseDto clienteApiResponseDto = getInfoCliente(criarPedidoDTO.idCliente());
        if(clienteApiResponseDto == null) {
            throw new ClienteNaoEncontradoException("Falha ao criar pedido! Cliente com ID: " + criarPedidoDTO.idCliente() + " não encontrado");
        }

        Double valorTotalPedido = 0.0;

        for (Map<String, Object> item : criarPedidoDTO.listaQtdProdutos()) {
            Long idProduto = Long.parseLong(item.get("idProduto").toString());
            // Pega os dados do produto pelo produto-service
            ProdutoApiResponseDto produtoApiResponseDto = getInfoProduto(idProduto);
            if(produtoApiResponseDto == null) {
                throw new ProdutoNaoEncontradoException("Produto com ID " + idProduto + " não encontrado");
            }

            valorTotalPedido = valorTotalPedido + ((Integer) item.get("quantidade") * produtoApiResponseDto.preco());

            // baixa do estoque cada produto na qtd pelo estoque-service
            EstoqueApiRequestDto estoqueApiRequestDto = new EstoqueApiRequestDto((Integer) item.get("quantidade"));
            EstoqueApiResponseDto retorno = baixarEstoque(idProduto, estoqueApiRequestDto);
            if(retorno == null) {
                throw new EstoqueInsuficienteException("Estoque insuficiente, ou produto nao encontrado. ID do Produto: " + idProduto + " quantidade: " + item.get("quantidade"));
            }

            System.out.println(retorno);
        }
//
        // Cria o pedido no banco de dados
        PedidoDTO pedidoDTO = pedidoController.criarPedido(criarPedidoDTO);
        log.info("Pedido " + pedidoDTO.idPedido() + " criado com sucesso! Prosseguindo para criaçao de ordem de pagamento");

        // Cria a ordem de pagamento pelo pagamento-sevice
        CriarPagamentoDTO criarPagamentoDTO = new CriarPagamentoDTO(
                pedidoDTO.idPedido(),
                clienteApiResponseDto.nome(),
                clienteApiResponseDto.email(),
                valorTotalPedido,
                StatusPagamento.EM_ABERTO
        );
        System.out.println("CRIAÇÃO DO PAGAMENTO: "+ criarPagamentoDTO);

        PagamentoDTO pagamentoDTO = criarPagamento(criarPagamentoDTO);
        if(pagamentoDTO == null) {
            throw new OrdemPagamentoNaoEncontradaException("Erro ao gerar ordem de pagamento.");
        }

        System.out.println("RESPOSTA DO PAGAMENTO: "+ pagamentoDTO);

//         Atualiza o pedido no banco de dados para salvar o id do pagamento
        PedidoDTO pedidoComPagamento = new PedidoDTO(
                pedidoDTO.idPedido(),
                clienteApiResponseDto.id(),
                pagamentoDTO.id(),
                criarPedidoDTO.listaQtdProdutos(),
                pedidoDTO.status()
        );
        pedidoController.editarPedido(pedidoComPagamento);

    }

    private final EstoqueApiResponseDto baixarEstoque(Long idProduto, EstoqueApiRequestDto estoqueApiRequestDto) {
        WebClient webClient = WebClient.create(estoqueServiceUrl);
        return webClient.post()
                .uri("/estoques/" + idProduto + "/baixar")
                .bodyValue(estoqueApiRequestDto)
                .retrieve()
                .bodyToMono(EstoqueApiResponseDto.class)
                .block(); // para nao ter que usar o tipo Mono<Estoque>
    }

    private final ClienteApiResponseDto getInfoCliente(Long idCliente) {
        WebClient webClient = WebClient.create(clienteServiceUrl);
        return webClient.get()
                .uri("/clientes/id/" + idCliente)
                .retrieve()
                .bodyToMono(ClienteApiResponseDto.class)
                .block(); // para nao ter que usar o tipo Mono<ClienteApiResponseDto>
    }

    private final ProdutoApiResponseDto getInfoProduto(Long idProduto) {
        WebClient webClient = WebClient.create(produtoServiceUrl);
        return webClient.get()
                .uri("/produtos/" + idProduto)
                .retrieve()
                .bodyToMono(ProdutoApiResponseDto.class)
                .block(); // para nao ter que usar o tipo Mono<ProdutoApiResponseDto>
    }

    private final PagamentoDTO criarPagamento(CriarPagamentoDTO criarPagamentoDTO) {
        WebClient webClient = WebClient.create(pagamentoServiceUrl);
        return webClient.post()
                .uri("/pagamentos/gerar")
                .bodyValue(criarPagamentoDTO)
                .retrieve()
                .bodyToMono(PagamentoDTO.class)
                .block(); // para nao ter que usar o tipo Mono<Estoque>
    }
}
