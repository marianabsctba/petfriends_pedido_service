package edu.br.infnet.petfriends.pedido.command;

public abstract class BaseCommand<T> {
    private final T id;

    protected BaseCommand(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
