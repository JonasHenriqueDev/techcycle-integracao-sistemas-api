package br.upe.is.controller;

import br.upe.is.domain.ApiResponse;
import br.upe.is.domain.Notificacao;
import br.upe.is.service.NotificationConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    public NotificationConsumer consumer;

    @GetMapping("/last-message")
    public ResponseEntity<?> getLastMessage() {
        try {
            String lastMessage = consumer.getLastMessage();
            if (lastMessage != null) {
                return ResponseEntity.ok(new ApiResponse("Mensagem recebida com sucesso", lastMessage));
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("Nenhuma mensagem consumida ainda", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Erro ao obter a mensagem", e.getMessage()));
        }
    }

    @GetMapping("/consume-all")
    public ResponseEntity<?> getAllMessages() {
        try {
            List<Notificacao> allMessages = consumer.getAllMessages();
            return ResponseEntity.ok(new ApiResponse("Mensagens consumidas com sucesso", allMessages));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Erro ao obter as mensagens", e.getMessage()));
        }
    }
}

