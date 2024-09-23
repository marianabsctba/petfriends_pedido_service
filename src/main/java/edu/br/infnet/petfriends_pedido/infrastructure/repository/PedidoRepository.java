package edu.br.infnet.petfriends_pedido.infrastructure.repository;

import edu.br.infnet.petfriends_pedido.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Pedido findByClienteId(Long clienteId);
}
