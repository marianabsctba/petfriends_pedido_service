package edu.br.infnet.petfriends.pedido.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoCriadoEvent extends BaseEvent<Long> {
    private final String descricao;
    private final double valor;
    private final int qauntidade;
    private final Long produtoId;

    public PedidoCriadoEvent(Long id, String descricao, double valor, int qauntdade, Long produtoId) {
        super(id);
        this.descricao = descricao;
        this.valor = valor;
        this.qauntidade = qauntdade;
        this.produtoId = produtoId;
    }

}
