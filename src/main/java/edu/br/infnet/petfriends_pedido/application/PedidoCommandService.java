package edu.br.infnet.petfriends_pedido.application;

import edu.br.infnet.petfriends_pedido.domain.commands.CriarPedidoCommand;
import edu.br.infnet.petfriends_pedido.domain.enums.PedidoStatus;
import edu.br.infnet.petfriends_pedido.domain.model.Pedido;
import edu.br.infnet.petfriends_pedido.infrastructure.clients.ClienteClient;
import edu.br.infnet.petfriends_pedido.infrastructure.clients.ProdutoClient;
import edu.br.infnet.petfriends_pedido.infrastructure.dto.ClienteDTO;
import edu.br.infnet.petfriends_pedido.infrastructure.dto.EnderecoDTO;
import edu.br.infnet.petfriends_pedido.infrastructure.dto.ProdutoDTO;
import edu.br.infnet.petfriends_pedido.infrastructure.repository.PedidoRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class PedidoCommandService {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private ClienteClient clienteClient;

    @Autowired
    private ProdutoClient produtoClient;

    @Autowired
    private PedidoRepository pedidoRepository;

    public void criarPedido(Long pedidoId, Long clienteId, Long produtoId) {
        try {
            ClienteDTO cliente = clienteClient.getCliente(clienteId);
            if (cliente == null || cliente.getEndereco() == null) {
                throw new RuntimeException("Informações do cliente não encontradas para o ID: " + clienteId);
            }
            ProdutoDTO produto = produtoClient.getProduto(produtoId);
            if (produto == null) {
                throw new RuntimeException("Produto não encontrado com o ID: " + produtoId);
            }
            String enderecoFormatado = formatarEndereco(cliente.getEndereco());
            Pedido pedido = Pedido.builder()
                    .clienteId(clienteId)
                    .produtoId(produtoId)
                    .status(PedidoStatus.NOVO)
                    .enderecoEntrega(enderecoFormatado)
                    .build();

            pedidoRepository.save(pedido);

            commandGateway.send(new CriarPedidoCommand(
                    pedidoId,
                    clienteId,
                    cliente.getNome(),          // Nome do cliente
                    cliente.getTelefone(),      // Telefone do cliente
                    produtoId,
                    enderecoFormatado
            ));

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Erro ao acessar serviços de Cliente ou Produto: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao criar o pedido: " + e.getMessage(), e);
        }
    }

    private String formatarEndereco(EnderecoDTO endereco) {
        return endereco.getRua() + ", " + endereco.getCidade() + ", " + endereco.getEstado() + " - " + endereco.getCep();
    }
}
