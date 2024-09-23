package edu.br.infnet.petfriends_pedido.domain.model;


import edu.br.infnet.petfriends_pedido.domain.enums.PedidoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long clienteId;

    @Column(nullable = false)
    private Long produtoId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    private String enderecoEntrega;

    public void fecharPedido() {
        this.status = PedidoStatus.FECHADO;
    }

    public void cancelarPedido() {
        this.status = PedidoStatus.CANCELADO;
    }

    public void prepararPedido() {
        this.status = PedidoStatus.EM_PREPARACAO;
    }

    public void despacharPedido() {
        this.status = PedidoStatus.EM_TRANSITO;
    }

    public void entregarPedido() {
        this.status = PedidoStatus.ENTREGUE;
    }

    public void estraviarPedido() {
        this.status = PedidoStatus.ESTRAVIADO;
    }

    public void devolverPedido() {
        this.status = PedidoStatus.DEVOLVIDO;
    }
}

