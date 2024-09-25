package edu.br.infnet.petfriends_pedido.interfaces.dto;


import lombok.Data;

@Data
public class PedidoEventoDto {
    private Long produtoId;
    private Long pedidoId;
    private int quantidade;

}