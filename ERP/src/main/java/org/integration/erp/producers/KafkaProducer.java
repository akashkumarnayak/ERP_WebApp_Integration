package org.integration.erp.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducer<T> implements IProducer<T> {


    private final KafkaTemplate<String, T> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, T message) {
        kafkaTemplate.send(topic, message);
    }
}
