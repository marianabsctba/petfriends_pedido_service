package edu.br.infnet.petfriends_pedido.domain.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoCriadoEvent extends BaseEvent<Long> {
    private final String descricao;
    private final double valor;
    private final int quantidade;
    private final Long produtoId;

    public PedidoCriadoEvent(Long id, String descricao, double valor, int quantidade, Long produtoId) {
        super(id);
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.produtoId = produtoId;
    }

}