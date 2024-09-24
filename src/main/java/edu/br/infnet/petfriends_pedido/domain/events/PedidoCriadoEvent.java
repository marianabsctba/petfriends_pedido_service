package edu.br.infnet.petfriends_pedido.domain.events;

import edu.br.infnet.petfriends_pedido.domain.enums.PedidoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCriadoEvent {
    private Long pedidoId;
    private Long clienteId;
    private String clienteNome;
    private String clienteTelefone;
    private PedidoStatus status;
    private String enderecoEntrega;
    private Long produtoId;
}
