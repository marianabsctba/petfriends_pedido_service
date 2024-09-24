package edu.br.infnet.petfriends_pedido.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private EnderecoDTO endereco;  // Endereço como um DTO próprio
}
