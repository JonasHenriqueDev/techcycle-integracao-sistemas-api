package br.upe.is.service;

import br.upe.is.domain.Item;
import br.upe.is.domain.PontoColeta;
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

    public void notifyNewCollectionPoint(PontoColeta pontoColeta) {
        kafkaTemplate.send(topicName,
                "Novo ponto de coleta adicionado: "
                        + "Id: " + pontoColeta.getId()
                        + ", Nome: " + pontoColeta.getNome()
                        + ", Latitude: " + pontoColeta.getLatitude()
                        + ", Longitude: " + pontoColeta.getLongitude());
    }

    public void notifyNewItemReceived(Item item) {
        kafkaTemplate.send(topicName,
                "Novo item recebido em um ponto de coleta: "
                        + "Id: " + item.getId()
                        + ", Nome: " + item.getNome()
                        + ", Tipo: " + item.getTipo()
                        + ", Ponto de coleta: " + item.getPontoColeta().getNome());
    }
}
