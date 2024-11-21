package br.upe.is.service;

import br.upe.is.domain.Notificacao;
import br.upe.is.repository.NotificacaoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class NotificationConsumer {

    private String lastMessage;
    private final List<String> allMessages = new ArrayList<>();

    @Autowired
    NotificacaoRepository notificacaoRepository;

    @KafkaListener(topics = "${spring.kafka.topic.name:notification-topic}", groupId = "notification-service")
    public void consume(String message) {
        System.out.println("Received message: " + message);
        this.lastMessage = message;
        synchronized (allMessages) { // Garantir thread safety para acessos simultâneos
            allMessages.add(message);
        }
    }

    @KafkaListener(topics = "${spring.kafka.topic.name:notification-topic}", groupId = "notification-service")
    public List<String> getAllMessages() {
        synchronized (allMessages) { // Retornar uma cópia para evitar modificações externas
            return new ArrayList<>(allMessages);
        }
    }
}