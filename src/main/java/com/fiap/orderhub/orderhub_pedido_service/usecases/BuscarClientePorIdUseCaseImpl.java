package com.fiap.orderhub.orderhub_pedido_service.usecases;

import br.com.orderhub.core.domain.usecases.clientes.BuscarClientePorId;
import br.com.orderhub.core.dto.clientes.ClienteDTO;
import br.com.orderhub.core.interfaces.IClienteGateway;
import com.fiap.orderhub.orderhub_pedido_service.exceptions.BuscarClienteException;
import com.fiap.orderhub.orderhub_pedido_service.persistence.ClienteEntity;
import com.fiap.orderhub.orderhub_pedido_service.persistence.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarClientePorIdUseCaseImpl {
    private final ClienteRepository clienteRepository;
    private final BuscarClientePorId buscarClientePorId;

    @Autowired
    public BuscarClientePorIdUseCaseImpl(ClienteRepository clienteRepository, IClienteGateway clienteGateway) {
        this.clienteRepository = clienteRepository;
        this.buscarClientePorId = new BuscarClientePorId(clienteGateway);
    }

    public ClienteDTO buscarPorId(String id) throws BuscarClienteException {
        // 1. Tenta buscar localmente
        var clienteEntityOpt = clienteRepository.findById(id.toString());
        if (clienteEntityOpt.isPresent()) {
            var cliente = clienteEntityOpt.get();
            return new ClienteDTO(
                    Long.valueOf(cliente.getId()),
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getDataNascimento(),
                    cliente.getEndereco(),
                    cliente.getNumeroContato(),
                    cliente.getEmail(),
                    cliente.getInfoPagamento()
            );
        }

        ClienteDTO clienteDTO = buscarClientePorId.run(Long.valueOf(id));

        // 3. Salva localmente
        ClienteEntity entity = new ClienteEntity();
        entity.setId(clienteDTO.id());
        entity.setNome(clienteDTO.nome());
        entity.setCpf(clienteDTO.cpf());
        entity.setDataNascimento(clienteDTO.dataNascimento());
        entity.setEndereco(clienteDTO.endereco());
        entity.setNumeroContato(clienteDTO.numeroContato());
        entity.setEmail(clienteDTO.email());
        entity.setInfoPagamento(clienteDTO.infoPagamento());
        clienteRepository.save(entity);

        return clienteDTO;
    }
}