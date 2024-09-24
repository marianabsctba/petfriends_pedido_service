package edu.br.infnet.petfriends_pedido.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransporteDTO {
    private Long pedidoId;
    private Long clienteId;
    private String clienteNome;
    private String clienteTelefone;
    private String status;
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
}
