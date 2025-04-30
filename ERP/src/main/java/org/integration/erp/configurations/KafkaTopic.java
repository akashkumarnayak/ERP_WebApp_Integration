package org.integration.erp.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Value("${spring.kafka.producer.po.310.topic}")
    private String poTopic;

    @Bean
    public NewTopic poTopic() {
        return TopicBuilder.name(poTopic).build();
    }

}
