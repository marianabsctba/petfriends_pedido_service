package edu.br.infnet.petfriends_pedido.domain.events;

import lombok.Getter;

@Getter
public abstract class BaseEvent<T> {
    private final T id;

    protected BaseEvent(T id) {
        this.id = id;
    }

}