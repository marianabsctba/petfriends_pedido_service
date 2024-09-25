package edu.br.infnet.petfriends_pedido.interfaces;

import edu.br.infnet.petfriends_pedido.application.PedidoQueryService;
import edu.br.infnet.petfriends_pedido.domain.model.Pedido;
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
        return pedidoQueryService.consultarPedidoPorId(id).orElse(null);
    }

}