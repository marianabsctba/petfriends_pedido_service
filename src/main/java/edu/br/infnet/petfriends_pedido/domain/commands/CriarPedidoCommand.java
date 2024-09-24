package edu.br.infnet.petfriends_pedido.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarPedidoCommand {
    private Long pedidoId;
    private Long clienteId;
    private String clienteNome;    // Nome do cliente
    private String clienteTelefone; // Telefone do cliente
    private Long produtoId;
    private String enderecoEntrega;
}
