package edu.br.infnet.petfriends.pedido.event;

public abstract class BaseEvent<T> {
    private final T id;

    protected BaseEvent(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
