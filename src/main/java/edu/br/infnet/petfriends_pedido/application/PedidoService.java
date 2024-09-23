package edu.br.infnet.petfriends_pedido.application;


import edu.br.infnet.petfriends_pedido.domain.enums.PedidoStatus;
import edu.br.infnet.petfriends_pedido.domain.events.PedidoCriadoEvent;
import edu.br.infnet.petfriends_pedido.domain.model.Pedido;
import edu.br.infnet.petfriends_pedido.infrastructure.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Pedido criarPedido(Long clienteId, Long produtoId, String enderecoEntrega) {
        Pedido pedido = Pedido.builder()
                .clienteId(clienteId)
                .produtoId(produtoId)
                .status(PedidoStatus.NOVO)
                .enderecoEntrega(enderecoEntrega)
                .build();
        pedidoRepository.save(pedido);

        PedidoCriadoEvent event = new PedidoCriadoEvent(pedido.getId(), clienteId, produtoId, enderecoEntrega);
        rabbitTemplate.convertAndSend("pedidoExchange", "pedidoRoutingKey", event);

        return pedido;
    }
}
