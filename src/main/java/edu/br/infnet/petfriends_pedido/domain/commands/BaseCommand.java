package edu.br.infnet.petfriends_pedido.domain.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class BaseCommand {

    @TargetAggregateIdentifier
    private final Long id;

    public BaseCommand(Long id) {
        this.id = id;
    }

}
