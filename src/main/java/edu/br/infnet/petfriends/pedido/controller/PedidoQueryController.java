package edu.br.infnet.petfriends.pedido.controller;

import edu.br.infnet.petfriends.pedido.exception.PedidoNotFoundException;
import edu.br.infnet.petfriends.pedido.model.Pedido;
import edu.br.infnet.petfriends.pedido.service.PedidoQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoQueryController {

    private final PedidoQueryService pedidoQueryService;

    public PedidoQueryController(PedidoQueryService pedidoQueryService) {
        this.pedidoQueryService = pedidoQueryService;
    }

    @GetMapping("/{id}/eventos")
    public List<Object> listarEventos(@PathVariable Long id) {
        return pedidoQueryService.listarEventos(id);
    }

    @GetMapping("/{id}")
    public Pedido obterPedidoPorId(@PathVariable Long id) {
        return pedidoQueryService.consultarPedidoPorId(id).orElseThrow(() -> new PedidoNotFoundException(id));
    }
}
