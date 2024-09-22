package edu.br.infnet.petfriends.cliente.repository;

import edu.br.infnet.petfriends.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
