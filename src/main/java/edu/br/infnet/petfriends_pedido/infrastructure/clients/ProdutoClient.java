package edu.br.infnet.petfriends_pedido.infrastructure.clients;

import edu.br.infnet.petfriends_pedido.infrastructure.dto.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produto-service", url = "http://localhost:8081/api/produtos")
public interface ProdutoClient {

    @GetMapping("/{produtoId}")
    ProdutoDTO getProduto(@PathVariable Long produtoId);
}
