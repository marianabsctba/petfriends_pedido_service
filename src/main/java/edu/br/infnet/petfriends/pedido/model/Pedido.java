package edu.br.infnet.petfriends.pedido.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Pedido{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private double valor;
    private int quantidade;
    private Long produtoId;

    // Construtor padrão
    public Pedido() {}

    // Construtor com parâmetros
    public Pedido(Long id, String descricao, double valor, int quantidade, Long produtoId) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.produtoId = produtoId;
    }


    @Override
    public String toString() {
        return "PedidoEntity{" +
                "id='" + id + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
