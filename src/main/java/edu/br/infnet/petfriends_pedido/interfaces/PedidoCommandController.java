package edu.br.infnet.petfriends_pedido.interfaces;


import edu.br.infnet.petfriends_pedido.application.PedidoCommandService;
import edu.br.infnet.petfriends_pedido.domain.model.CriarPedidoRequest;
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