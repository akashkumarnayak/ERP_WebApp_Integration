package org.integration.erp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class PoDltProcessor {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.producer.po.310.topic}")
    String redirectTopic;

    @KafkaListener( topics = "${kafka.topic.po310.dlt}", groupId = "${kafka.group-id.po310.dlt}")
    public void ReprocessPurchaseOrder(String purchaseOrder, Acknowledgment ack){
        kafkaTemplate.send(redirectTopic, purchaseOrder);
        ack.acknowledge();
    }

}
