package edu.br.infnet.petfriends_pedido.application;

import edu.br.infnet.petfriends_pedido.domain.model.Pedido;
import edu.br.infnet.petfriends_pedido.infrastructure.repository.PedidoRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoQueryService {
    private final EventStore eventStore;

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoQueryService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public List<Object> listarEventos(Long pedidoId) {
        return eventStore.readEvents(pedidoId.toString()).asStream().collect(Collectors.toList());
    }

    public Optional<Pedido> consultarPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }
}