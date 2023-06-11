package com.example.testtaskproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${kafka-topic.strings-under-100}")
    private String stringsUnder100TopicName;

    @Bean
    NewTopic stringUnder100Producer() {
        return TopicBuilder.name(stringsUnder100TopicName).partitions(1).build();
    }
}
