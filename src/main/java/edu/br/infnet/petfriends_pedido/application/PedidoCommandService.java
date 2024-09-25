package edu.br.infnet.petfriends_pedido.application;

import edu.br.infnet.petfriends_pedido.domain.commands.BaseCommand;
import edu.br.infnet.petfriends_pedido.domain.commands.CriarPedidoCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoCommandService {
    private final CommandGateway commandGateway;

    public PedidoCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public void criarPedido(Long id, String descricao, double valor, int quantidade, Long produtoId) {
        CriarPedidoCommand command = new CriarPedidoCommand(id, descricao, valor, quantidade, produtoId);
        commandGateway.send(command);
    }
}