package edu.br.infnet.petfriends_pedido.infrastructure.clients;
import edu.br.infnet.petfriends_pedido.infrastructure.dto.TransporteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "transporte-service", url = "http://localhost:8083/api/transportes")
public interface TransporteClient {

    @PostMapping("/iniciar")
    void iniciarTransporte(@RequestBody TransporteDTO transporteDTO);

    @GetMapping("/{pedidoId}")
    TransporteDTO buscarPorPedidoId(@PathVariable Long pedidoId);

    @PutMapping("/atualizarStatus/{pedidoId}")
    void atualizarTransporte(@RequestBody TransporteDTO transporteDTO);
}
