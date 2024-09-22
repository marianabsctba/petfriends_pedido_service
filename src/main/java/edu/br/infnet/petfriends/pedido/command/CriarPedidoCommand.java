package edu.br.infnet.petfriends.pedido.command;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarPedidoCommand extends BaseCommand<Long> {
    private final String descricao;
    private final double valor;
    private final int quantidade;
    private final Long produtoId;

    public CriarPedidoCommand(Long id, String descricao, double valor, int quantidade, Long produtoId) {
        super(id);
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.produtoId = produtoId;
    }
}
