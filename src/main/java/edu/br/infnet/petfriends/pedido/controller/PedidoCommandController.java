package edu.br.infnet.petfriends.pedido.controller;

import edu.br.infnet.petfriends.pedido.model.CriarPedidoRequest;
import edu.br.infnet.petfriends.pedido.service.PedidoCommandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoCommandController {

    private final PedidoCommandService pedidoCommandService;

    public PedidoCommandController(PedidoCommandService pedidoCommandService) {
        this.pedidoCommandService = pedidoCommandService;
    }

    @PostMapping
    public void criarPedido(@RequestBody CriarPedidoRequest request) {
        pedidoCommandService.criarPedido(request.getId(), request.getDescricao(), request.getValor(), request.getQuantidade(),request.getProdutoId());
    }
}

