package edu.br.infnet.petfriends_pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PetFriendsPedidoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetFriendsPedidoApplication.class, args);
    }
}

