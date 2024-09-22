package edu.br.infnet.petfriends.pedido.repository;

import edu.br.infnet.petfriends.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}
