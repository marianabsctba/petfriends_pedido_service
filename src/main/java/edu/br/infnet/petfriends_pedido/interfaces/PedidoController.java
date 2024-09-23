package edu.br.infnet.petfriends_pedido.interfaces;

import edu.br.infnet.petfriends_pedido.application.PedidoService;
import edu.br.infnet.petfriends_pedido.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestParam Long clienteId,
                                              @RequestParam Long produtoId,
                                              @RequestParam String enderecoEntrega) {
        Pedido pedido = pedidoService.criarPedido(clienteId, produtoId, enderecoEntrega);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
