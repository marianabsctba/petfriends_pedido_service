package edu.br.infnet.petfriends_pedido.domain.aggregate;

import edu.br.infnet.petfriends_pedido.domain.commands.CriarPedidoCommand;
import edu.br.infnet.petfriends_pedido.domain.events.PedidoCriadoEvent;
import edu.br.infnet.petfriends_pedido.domain.events.PedidoPreparadoParaEnvioEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class PedidoAggregate {

    @AggregateIdentifier
    private Long id;
    private String descricao;
    private double valor;
    private int quantidade;
    private Long produtoId;

    public PedidoAggregate() { }

    @CommandHandler
    public PedidoAggregate(CriarPedidoCommand command) {
        apply(new PedidoCriadoEvent(command.getId(), command.getDescricao(), command.getValor(), command.getQuantidade(), command.getProdutoId()));
    }

    @EventSourcingHandler
    public void on(PedidoCriadoEvent event) {
        this.id = event.getId();
        this.descricao = event.getDescricao();
        this.valor = event.getValor();
        this.quantidade = event.getQuantidade();
        this.produtoId = event.getProdutoId();

        apply(new PedidoPreparadoParaEnvioEvent(this.id, "Rua Exemplo, 123", "Cliente Exemplo"));
    }
}
