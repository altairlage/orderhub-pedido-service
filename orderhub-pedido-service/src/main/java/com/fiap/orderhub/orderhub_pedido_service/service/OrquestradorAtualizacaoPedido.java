package com.fiap.orderhub.orderhub_pedido_service.service;

import br.com.orderhub.core.controller.PedidoController;
import br.com.orderhub.core.domain.enums.StatusPagamento;
import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.dto.pedidos.PedidoDTO;
import com.fiap.orderhub.orderhub_pedido_service.dto.AtualizacaoStatusPeditoApiRequestDto;
import com.fiap.orderhub.orderhub_pedido_service.dto.EstoqueApiRequestDto;
import com.fiap.orderhub.orderhub_pedido_service.dto.EstoqueApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Slf4j
@Service
public class OrquestradorAtualizacaoPedido {

    private final PedidoController pedidoController;

    public OrquestradorAtualizacaoPedido(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
    }

    public void atualizarStatusPedido(AtualizacaoStatusPeditoApiRequestDto atualizacaoPeditoApiRequestDto) {
        if(atualizacaoPeditoApiRequestDto.statusPagamento()== StatusPagamento.FECHADO_FALHA_PAGAMENTO) {
           PedidoDTO pedidoDTO = pedidoController.buscarPedidoPorId(atualizacaoPeditoApiRequestDto.idPedido());

            for (Map<String, Object> item : pedidoDTO.listaQtdProdutos()) {
                Long idProduto = (Long) item.get("idProduto");
                EstoqueApiRequestDto estoqueApiRequestDto = new EstoqueApiRequestDto((Integer) item.get("quantidade"));

                EstoqueApiResponseDto retorno = reporEstoque(idProduto, estoqueApiRequestDto);
                if(retorno != null) {
                    log.info("Retornado ao estoque" + item.get("quantidade") + " itens do produto " + item.get("idProduto"));
                } else {
                    log.info("Erro ao retornar estoque do produto" + item.get("idProduto"));
                    // aqui poderiamos colocar o produto e quantidade em uma fila ou dead letter queue para ser tentado novamente mais tarde
                }
            }
            pedidoController.editarPedidoStatus(pedidoDTO.idPedido(), StatusPedido.FECHADO_SEM_CREDITO);
        }
    }

    private final EstoqueApiResponseDto reporEstoque(Long idProduto, EstoqueApiRequestDto estoqueApiRequestDto) {
        WebClient webClient = WebClient.create("http://localhost:8080");
        return webClient.post()
                .uri("/api/estoques/" + idProduto + "/repor")
                .bodyValue(estoqueApiRequestDto)
                .retrieve()
                .bodyToMono(EstoqueApiResponseDto.class)
                .block(); // para nao ter que usar o tipo Mono<Estoque>
    }
}
