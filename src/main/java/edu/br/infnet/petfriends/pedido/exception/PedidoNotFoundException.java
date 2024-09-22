package edu.br.infnet.petfriends.pedido.exception;

public class PedidoNotFoundException extends RuntimeException {

    public PedidoNotFoundException(Long id) {
        super("Pedido com ID " + id + " não foi encontrado.");
    }

    public PedidoNotFoundException(Long id, Throwable cause) {
        super("Pedido com ID " + id + " não foi encontrado.", cause);
    }
}