package edu.br.infnet.petfriends_pedido.domain.events;

import lombok.Getter;

@Getter
public class PedidoPreparadoParaEnvioEvent extends BaseEvent<Long> {
    private final String enderecoEntrega;
    private final String cliente;

    public PedidoPreparadoParaEnvioEvent(Long id, String enderecoEntrega, String cliente) {
        super(id);
        this.enderecoEntrega = enderecoEntrega;
        this.cliente = cliente;
    }
}

