package br.upe.is.service;

import br.upe.is.domain.Anuncio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {


    private final KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${spring.kafka.topic.name:notification-topic}")
    private String topicName;

    public NotificationProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void notifyNewCollectionPoint(Anuncio pontoColeta) {
        kafkaTemplate.send(topicName,
                "Novo produto anunciado no marketplace: "
                        + "Id: " + pontoColeta.getId()
                        + ", Título: " + pontoColeta.getTitulo()
                        + ", Preço: " + pontoColeta.getPreco());
    }
}
