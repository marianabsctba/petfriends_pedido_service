package edu.br.infnet.petfriends_pedido.interfaces;

import edu.br.infnet.petfriends_pedido.application.PedidoCommandService;
import edu.br.infnet.petfriends_pedido.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoCommandService pedidoCommandService;

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestParam Long clienteId,
                                              @RequestParam Long produtoId) {
        Long pedidoId = System.currentTimeMillis();
        try {
            pedidoCommandService.criarPedido(pedidoId, clienteId, produtoId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pedido criado com sucesso! ID: " + pedidoId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar pedido: " + e.getMessage());
        }
    }
}
