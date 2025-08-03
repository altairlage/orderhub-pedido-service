package com.fiap.orderhub.orderhub_pedido_service.gateway.impl;

import br.com.orderhub.core.domain.entities.Pagamento;
import br.com.orderhub.core.domain.enums.StatusPagamento;
import br.com.orderhub.core.dto.clientes.ClienteDTO;
import br.com.orderhub.core.interfaces.IPagamentoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PagamentoGatewayImpl implements IPagamentoGateway {
    @Override
    public Pagamento gerarOrdemPagamento(ClienteDTO clienteDTO) throws Exception {
        return null;
    }

    @Override
    public Pagamento fecharOrdemPagamento(Long idOrdemPagamento, StatusPagamento statusPagamento) {
        return null;
    }

    @Override
    public Pagamento buscarOrderPagamentoPorId(Long id) {
        return null;
    }
}
