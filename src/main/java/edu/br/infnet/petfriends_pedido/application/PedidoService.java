package edu.br.infnet.petfriends_pedido.application;

import edu.br.infnet.petfriends_pedido.domain.enums.PedidoStatus;
import edu.br.infnet.petfriends_pedido.domain.events.PedidoCriadoEvent;
import edu.br.infnet.petfriends_pedido.domain.model.Pedido;
import edu.br.infnet.petfriends_pedido.infrastructure.clients.ClienteClient;
import edu.br.infnet.petfriends_pedido.infrastructure.dto.ClienteDTO;
import edu.br.infnet.petfriends_pedido.infrastructure.dto.EnderecoDTO;
import edu.br.infnet.petfriends_pedido.infrastructure.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ClienteClient clienteClient;

    @Transactional
    public Pedido criarPedido(Long clienteId, Long produtoId) {
        try {
            // Obter as informações do cliente
            ClienteDTO clienteDTO = clienteClient.getCliente(clienteId);
            if (clienteDTO == null || clienteDTO.getEndereco() == null) {
                throw new RuntimeException("Informações do cliente não encontradas para o ID: " + clienteId);
            }

            // Formatar o endereço do cliente
            String enderecoFormatado = formatarEndereco(clienteDTO.getEndereco());

            // Criar e salvar o objeto Pedido
            Pedido pedido = Pedido.builder()
                    .clienteId(clienteId)
                    .produtoId(produtoId)
                    .status(PedidoStatus.NOVO)
                    .enderecoEntrega(enderecoFormatado)
                    .build();

            Pedido pedidoSalvo = pedidoRepository.save(pedido);

            // Criar o evento de pedido criado com o produtoId incluído
            PedidoCriadoEvent event = new PedidoCriadoEvent(
                    pedidoSalvo.getId(),
                    clienteId,
                    clienteDTO.getNome(),        // Nome do cliente obtido do ClienteDTO
                    clienteDTO.getTelefone(),    // Telefone do cliente obtido do ClienteDTO
                    PedidoStatus.NOVO,
                    enderecoFormatado,           // Endereço formatado
                    produtoId                    // ProdutoId adicionado
            );

            // Enviar o evento via RabbitMQ
            rabbitTemplate.convertAndSend("pedidoExchange", "pedidoRoutingKey", event);
            System.out.println("Evento de Pedido Criado enviado: " + event);

            return pedidoSalvo;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar pedido: " + e.getMessage(), e);
        }
    }

    private String formatarEndereco(EnderecoDTO endereco) {
        return endereco.getRua() + ", " + endereco.getCidade() + ", " + endereco.getEstado() + " - " + endereco.getCep();
    }
}
