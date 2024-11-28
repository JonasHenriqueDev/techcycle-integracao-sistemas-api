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

        saveNotification(message);

        synchronized (allMessages) { // Garantir thread safety para acessos simultâneos
            allMessages.add(message);
        }
    }

    private void saveNotification(String message) {
        Notificacao notificacao = new Notificacao();
        notificacao.setMessage(message);

        notificacaoRepository.save(notificacao);
    }

    @KafkaListener(topics = "${spring.kafka.topic.name:notification-topic}", groupId = "notification-service")
    public List<Notificacao> getAllMessages() {
        return notificacaoRepository.findAll();
    }
}