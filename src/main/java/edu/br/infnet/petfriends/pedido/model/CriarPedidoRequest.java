package edu.br.infnet.petfriends.pedido.model;

import lombok.Data;

@Data
public class CriarPedidoRequest {

    private Long id;
    private String descricao;
    private double valor;
    private int quantidade;
    private Long produtoId;

    public CriarPedidoRequest() {}

    public CriarPedidoRequest(Long id, String descricao, double valor, int quantidade, Long produtoId) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.produtoId = produtoId;
    }

    @Override
    public String toString() {
        return "CriarPedidoRequest{" +
                "id='" + id + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
