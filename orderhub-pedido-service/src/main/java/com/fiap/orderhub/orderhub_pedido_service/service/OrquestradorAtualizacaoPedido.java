package com.fiap.orderhub.orderhub_pedido_service.service;

import br.com.orderhub.core.controller.PedidoController;
import br.com.orderhub.core.domain.enums.StatusPagamento;
import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.dto.pedidos.PedidoDTO;
import br.com.orderhub.core.exceptions.EstoqueNaoEncontradoException;
import com.fiap.orderhub.orderhub_pedido_service.dto.AtualizacaoStatusPedidoApiRequestDto;
import com.fiap.orderhub.orderhub_pedido_service.dto.EstoqueApiRequestDto;
import com.fiap.orderhub.orderhub_pedido_service.dto.EstoqueApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Slf4j
@Service
public class OrquestradorAtualizacaoPedido {

    private final PedidoController pedidoController;

    @Value("${estoque.service.url}")
    private String estoqueServiceUrl;

    public OrquestradorAtualizacaoPedido(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
    }

    public void atualizarStatusPedido(AtualizacaoStatusPedidoApiRequestDto atualizacaoPeditoApiRequestDto) {
        if(atualizacaoPeditoApiRequestDto.statusPagamento()== StatusPagamento.FECHADO_FALHA_PAGAMENTO) {
           PedidoDTO pedidoDTO = pedidoController.buscarPedidoPorId(atualizacaoPeditoApiRequestDto.idPedido());

            for (Map<String, Object> item : pedidoDTO.listaQtdProdutos()) {
                Long idProduto = (Long) item.get("idProduto");
                EstoqueApiRequestDto estoqueApiRequestDto = new EstoqueApiRequestDto((Integer) item.get("quantidade"));

                EstoqueApiResponseDto retorno = reporEstoque(idProduto, estoqueApiRequestDto);
                if(retorno != null) {
                    log.info("Retornado ao estoque" + item.get("quantidade") + " itens do produto " + item.get("idProduto"));
                } else {
                    throw new EstoqueNaoEncontradoException("Erro ao retornar estoque do produto" + item.get("idProduto"));
                    // aqui poderiamos colocar o produto e quantidade em uma fila ou dead letter queue para ser tentado novamente mais tarde
                }
            }
            pedidoController.editarPedidoStatus(pedidoDTO.idPedido(), StatusPedido.FECHADO_SEM_CREDITO);
        } else if(atualizacaoPeditoApiRequestDto.statusPagamento()== StatusPagamento.FECHADO_COM_SUCESSO) {
            PedidoDTO pedidoDTO = pedidoController.buscarPedidoPorId(atualizacaoPeditoApiRequestDto.idPedido());
            pedidoController.editarPedidoStatus(pedidoDTO.idPedido(), StatusPedido.FECHADO_SUCESSO);
        }
    }

    private final EstoqueApiResponseDto reporEstoque(Long idProduto, EstoqueApiRequestDto estoqueApiRequestDto) {
        WebClient webClient = WebClient.create(estoqueServiceUrl);
        return webClient.post()
                .uri("/api/estoques/" + idProduto + "/repor")
                .bodyValue(estoqueApiRequestDto)
                .retrieve()
                .bodyToMono(EstoqueApiResponseDto.class)
                .block(); // para nao ter que usar o tipo Mono<Estoque>
    }
}
