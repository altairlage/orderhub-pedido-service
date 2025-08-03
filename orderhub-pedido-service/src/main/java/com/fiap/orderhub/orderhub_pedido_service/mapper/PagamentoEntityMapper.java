package com.fiap.orderhub.orderhub_pedido_service.mapper;

import br.com.orderhub.core.domain.entities.Pagamento;
import com.fiap.orderhub.orderhub_pedido_service.persistence.PagamentoEntity;
import br.com.orderhub.core.domain.enums.StatusPagamento;

public class PagamentoEntityMapper {
    public static Pagamento entityToDomain(PagamentoEntity pagamentoEntity){
        return new Pagamento(
            pagamentoEntity.getId(),
            StatusPagamento.valueOf(pagamentoEntity.getStatusPagamento())
        );
    }

    public static PagamentoEntity domainToEntity(Pagamento pagamento){
        return new PagamentoEntity(
                pagamento.getId(),
                null, // nomeCliente
                null, // emailCliente
                null, // valorTotalOrdemPagamento
                pagamento.getStatus().toString()
        );
    }
}
