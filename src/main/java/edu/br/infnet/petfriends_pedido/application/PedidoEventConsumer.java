package edu.br.infnet.petfriends_pedido.application;

import edu.br.infnet.petfriends_pedido.domain.enums.PedidoStatus;
import edu.br.infnet.petfriends_pedido.domain.events.PedidoCriadoEvent;
import edu.br.infnet.petfriends_pedido.infrastructure.clients.ClienteClient;
import edu.br.infnet.petfriends_pedido.infrastructure.dto.EnderecoDTO;
import edu.br.infnet.petfriends_pedido.infrastructure.dto.TransporteDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class PedidoEventConsumer {

    @Autowired
    private ClienteClient clienteClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "${broker.queue.pedido.name}")
    public void processarEventoPedido(PedidoCriadoEvent event) {
        try {
            PedidoStatus status = event.getStatus();

            switch (status) {
                case EM_TRANSITO:
                case ENTREGUE:
                    enviarEventoParaTransporte(event, status);
                    break;
                case DEVOLVIDO:
                case EXTRAVIADO:
                    tratarProblemaTransporte(event);
                    break;
                default:
                    System.out.println("Evento de pedido com status não processado: " + status);
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Status de pedido inválido: " + event.getStatus());
        } catch (Exception e) {
            System.err.println("Erro ao processar evento de pedido: " + e.getMessage());
        }
    }

    private void enviarEventoParaTransporte(PedidoCriadoEvent event, PedidoStatus status) {
        try {
            EnderecoDTO enderecoDTO = clienteClient.getEnderecoCliente(event.getClienteId());

            if (enderecoDTO == null) {
                throw new RuntimeException("Endereço do cliente não encontrado para o clienteId: " + event.getClienteId());
            }

            TransporteDTO transporteDTO = new TransporteDTO(
                    event.getPedidoId(),
                    event.getClienteId(),
                    event.getClienteNome(),
                    event.getClienteTelefone(),
                    status.name(),
                    enderecoDTO.getRua(),
                    enderecoDTO.getCidade(),
                    enderecoDTO.getEstado(),
                    enderecoDTO.getCep()
            );

            rabbitTemplate.convertAndSend("transporteExchange", "transporteRoutingKey", transporteDTO);
            System.out.println("Evento de transporte enviado para o pedido: " + event.getPedidoId() + ", Status: " + status);
        } catch (Exception e) {
            System.err.println("Erro ao enviar evento de transporte: " + e.getMessage());
        }
    }

    private void tratarProblemaTransporte(PedidoCriadoEvent event) {
        try {
            TransporteDTO transporteDTO = new TransporteDTO(
                    event.getPedidoId(),
                    event.getClienteId(),
                    event.getClienteNome(),
                    event.getClienteTelefone(),
                    "Problema detectado", // Usando uma mensagem de status de problema
                    "", "", "", "" // Não precisamos do endereço para problema de transporte
            );

            rabbitTemplate.convertAndSend("transporteExchange", "transporteRoutingKey", transporteDTO);
            System.out.println("Problema de transporte tratado para o pedido: " + event.getPedidoId() + " com status: " + event.getStatus());
        } catch (Exception e) {
            System.err.println("Erro ao tratar problema de transporte: " + e.getMessage());
        }
    }
}
