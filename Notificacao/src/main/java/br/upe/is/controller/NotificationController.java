package br.upe.is.controller;

import br.upe.is.domain.Notificacao;
import br.upe.is.service.NotificationConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationConsumer consumer;

    @GetMapping("/last-message")
    public String getLastMessage() {
        String lastMessage = consumer.getLastMessage();
        return lastMessage != null ? lastMessage : "Nenhuma mensagem consumida ainda.";
    }

    @GetMapping("/consume-all")
    public List<String> getAllMessages(){
        List<String> allMessages = consumer.getAllMessages();
        return allMessages;
    }
}
