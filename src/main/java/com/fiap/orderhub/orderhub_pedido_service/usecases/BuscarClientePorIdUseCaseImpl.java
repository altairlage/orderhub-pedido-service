package com.fiap.orderhub.orderhub_pedido_service.usecases;

import br.com.orderhub.core.domain.entities.Cliente;
import br.com.orderhub.core.domain.presenters.ClientePresenter;
import br.com.orderhub.core.domain.usecases.clientes.BuscarClientePorId;
import br.com.orderhub.core.dto.clientes.ClienteDTO;
import br.com.orderhub.core.interfaces.IClienteGateway;
import com.fiap.orderhub.orderhub_pedido_service.exceptions.BuscarClienteException;
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
        var clienteEntityOpt = clienteRepository.findById(Long.valueOf(id));
        if (clienteEntityOpt.isPresent()) {
            var cliente = clienteEntityOpt.get();
            return ClientePresenter.ToDTO(cliente);
        }

        ClienteDTO clienteDTO = buscarClientePorId.run(Long.valueOf(id));

        // 3. Salva localmente
        Cliente entity = ClientePresenter.ToDomain(clienteDTO);
        clienteRepository.save(entity);

        return clienteDTO;
    }
}