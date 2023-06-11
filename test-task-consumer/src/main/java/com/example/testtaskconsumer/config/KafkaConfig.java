package com.example.testtaskconsumer.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaConfig {

    @Value("${kafka-topic.strings-under-100}")
    private String stringsUnder100TopicName;

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String DEAD_LETTERS_SUFFIX = ".DLT";

    @Bean
    NewTopic dtosToGetDiff() {
        return TopicBuilder.name(stringsUnder100TopicName).partitions(1).build();
    }

    @Bean
    NewTopic dtosToGetDiffDlt() {
        return TopicBuilder.name(stringsUnder100TopicName + DEAD_LETTERS_SUFFIX).partitions(1).build();
    }

    @Bean
    public DefaultErrorHandler eh() {
        return new DefaultErrorHandler((rec, ex) -> {
            log.info("Recovered: " + rec);
            kafkaTemplate.send(rec.topic() + DEAD_LETTERS_SUFFIX, rec.value());
        }, new FixedBackOff(0L, 0L));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(ConsumerFactory<String, Object> cf) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(cf);
        factory.setMessageConverter(new JsonMessageConverter());
        factory.setCommonErrorHandler(eh());
        var backOff = new FixedBackOff();
        backOff.setMaxAttempts(2L);
        backOff.setInterval(5000);
        return factory;
    }
}
