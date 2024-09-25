package edu.br.infnet.petfriends_pedido.domain.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CriarPedidoCommand extends BaseCommand {

    private String descricao;
    private double valor;
    private int quantidade;
    private Long produtoId;

    public CriarPedidoCommand(Long id, String descricao, double valor, int quantidade, Long produtoId) {
        super(id);
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.produtoId = produtoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }
}
