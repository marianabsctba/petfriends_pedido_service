package edu.br.infnet.petfriends_pedido.domain.commands;


import lombok.Getter;

@Getter
public abstract class BaseCommand<T> {
    private final T id;

    protected BaseCommand(T id) {
        this.id = id;
    }

}