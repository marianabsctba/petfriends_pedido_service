package edu.br.infnet.petfriends_pedido.domain.events;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoCriadoEvent {
    private Long pedidoId;
    private Long clienteId;
    private Long produtoId;
    private String enderecoEntrega;
}
