package edu.br.infnet.petfriends_pedido.domain.aggregate;

import edu.br.infnet.petfriends_pedido.domain.commands.CriarPedidoCommand;
import edu.br.infnet.petfriends_pedido.domain.enums.PedidoStatus;
import edu.br.infnet.petfriends_pedido.domain.events.PedidoCriadoEvent;
import edu.br.infnet.petfriends_pedido.infrastructure.dto.EnderecoDTO;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class PedidoAggregate {

    @AggregateIdentifier
    private Long pedidoId;
    private Long clienteId;
    private Long produtoId;
    private String enderecoEntrega;
    private PedidoStatus status;

    public PedidoAggregate() {
    }

    @CommandHandler
    public PedidoAggregate(CriarPedidoCommand command) {
        if (command.getClienteId() == null || command.getProdutoId() == null) {
            throw new IllegalArgumentException("ClienteId ou ProdutoId não podem ser nulos.");
        }

        this.pedidoId = command.getPedidoId();
        this.clienteId = command.getClienteId();
        this.produtoId = command.getProdutoId();
        this.status = PedidoStatus.NOVO;
        this.enderecoEntrega = command.getEnderecoEntrega();

        apply(new PedidoCriadoEvent(
                command.getPedidoId(),
                command.getClienteId(),
                command.getClienteNome(),
                command.getClienteTelefone(),
                this.status,
                this.enderecoEntrega,
                command.getProdutoId()
        ));
    }

    @EventSourcingHandler
    public void on(PedidoCriadoEvent event) {
        this.pedidoId = event.getPedidoId();
        this.clienteId = event.getClienteId();
        this.produtoId = event.getProdutoId();
        this.enderecoEntrega = event.getEnderecoEntrega();
        this.status = event.getStatus();
    }

    private String formatarEndereco(EnderecoDTO endereco) {
        if (endereco == null || endereco.getRua() == null || endereco.getCidade() == null || endereco.getEstado() == null || endereco.getCep() == null) {
            throw new IllegalArgumentException("Endereço inválido.");
        }
        return endereco.getRua() + ", " + endereco.getCidade() + ", " + endereco.getEstado() + " - " + endereco.getCep();
    }
}
