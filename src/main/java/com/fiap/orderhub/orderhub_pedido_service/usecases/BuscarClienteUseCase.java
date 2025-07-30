package com.fiap.orderhub.orderhub_pedido_service.usecases;

import br.com.orderhub.core.dto.clientes.ClienteDTO;
import com.fiap.orderhub.orderhub_pedido_service.exceptions.BuscarClienteException;
import com.fiap.orderhub.orderhub_pedido_service.exceptions.ClienteNotFoundException;
import com.fiap.orderhub.orderhub_pedido_service.persistence.ClienteEntity;
import com.fiap.orderhub.orderhub_pedido_service.persistence.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarClienteUseCase {
    private final ClienteRepository clienteRepository;

    public ClienteDTO buscarPorId(String id) throws BuscarClienteException {
        try {
            ClienteEntity cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado para o id: " + id));
            return new ClienteDTO(
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getDataNascimento(),
                    cliente.getEndereco(),
                    cliente.getNumeroContato(),
                    cliente.getEmail(),
                    cliente.getInfoPagamento()
            );
        } catch (Exception e) {
            throw new BuscarClienteException("Erro ao buscar cliente", e);
        }
    }
}