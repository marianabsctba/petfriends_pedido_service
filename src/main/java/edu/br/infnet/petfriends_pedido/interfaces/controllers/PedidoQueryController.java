package edu.br.infnet.petfriends_pedido.interfaces.controllers;

import edu.br.infnet.petfriends_pedido.application.PedidoQueryService;
import edu.br.infnet.petfriends_pedido.domain.model.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoQueryController {

    private final PedidoQueryService pedidoQueryService;

    public PedidoQueryController(PedidoQueryService pedidoQueryService) {
        this.pedidoQueryService = pedidoQueryService;
    }

    @GetMapping("/{id}/eventos")
    public ResponseEntity<?> listarEventos(@PathVariable Long id) {
        List<Object> eventos = pedidoQueryService.listarEventos(id);
        if (eventos.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Nenhum evento encontrado para o pedido ID: " + id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterPedidoPorId(@PathVariable Long id) {
        return pedidoQueryService.consultarPedidoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Pedido não encontrado para o ID: " + id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body((Pedido) response);
                });
    }
}
