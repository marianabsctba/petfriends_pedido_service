package edu.br.infnet.petfriends_pedido.interfaces.controllers;


import edu.br.infnet.petfriends_pedido.application.PedidoCommandService;
import edu.br.infnet.petfriends_pedido.interfaces.dto.CriarPedidoRequest;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoCommandController {

    private final PedidoCommandService pedidoCommandService;

    public PedidoCommandController(PedidoCommandService pedidoCommandService) {
        this.pedidoCommandService = pedidoCommandService;
    }

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestBody CriarPedidoRequest request) {
        pedidoCommandService.criarPedido(request.getId(), request.getDescricao(), request.getValor(), request.getQuantidade(), request.getProdutoId());
        return ResponseEntity.ok("Pedido criado com sucesso");
    }
}
