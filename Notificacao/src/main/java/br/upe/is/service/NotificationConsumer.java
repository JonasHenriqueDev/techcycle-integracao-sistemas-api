package br.upe.is.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Data
public class NotificationConsumer {

    private String lastMessage;

    @KafkaListener(topics = "${spring.kafka.topic.name:notification-topic}", groupId = "notification-service")
    public void consume(String message) {
        System.out.println("Received message: " + message);
        this.lastMessage = message;
    }
}