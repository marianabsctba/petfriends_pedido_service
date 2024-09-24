package edu.br.infnet.petfriends_pedido.infrastructure.clients;

import edu.br.infnet.petfriends_pedido.infrastructure.dto.ClienteDTO;
import edu.br.infnet.petfriends_pedido.infrastructure.dto.EnderecoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-service", url = "http://localhost:8081/api/clientes")
public interface ClienteClient {
    @GetMapping("/{id}")
    ClienteDTO getCliente(@PathVariable("id") Long clienteId);

    @GetMapping("/{id}/endereco")
    EnderecoDTO getEnderecoCliente(@PathVariable("id") Long clienteId);
}
