package org.integration.erp.configurations;

//import com.fasterxml.jackson.databind.JsonSerializer;
////import org.apache.kafka.common.serialization.StringSerializer;
//import com.fasterxml.jackson.databind.ser.std.StringSerializer;

import org.apache.kafka.common.serialization.StringSerializer;
import org.integration.erp.dtos.PoKafkaPayloadDto;
import org.springframework.kafka.support.serializer.JsonSerializer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.integration.erp.entities.PurchaseOrder;
import org.integration.erp.producers.IProducer;
import org.integration.erp.producers.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    @Autowired
    private Environment env;

    private Map<String, Object> baseProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getRequiredProperty("spring.kafka.bootstrap-servers"));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    private <T> ProducerFactory<String, T> producerFactory() {

        return new DefaultKafkaProducerFactory<>(baseProducerConfigs());
    }

    private <T> KafkaTemplate<String, T> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public IProducer<PurchaseOrder> poProducer() {
        return new KafkaProducer<>(kafkaTemplate());
    }

    @Bean
    public IProducer<PoKafkaPayloadDto> poPayloadProducer() {
        return new KafkaProducer<>(kafkaTemplate());
    }


//    @Bean
//    public ProducerFactory<String, PurchaseOrder> poProducerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }



}
