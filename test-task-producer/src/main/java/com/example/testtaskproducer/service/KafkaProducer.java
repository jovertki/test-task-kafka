package com.example.testtaskproducer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, Object obj) {
        var callback = kafkaTemplate.send(topic, obj);
        callback.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.debug("Sent message to topic " + topic + " =[" + obj.toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message to topic " + topic + " =["
                        + obj.toString() + "] due to : " + ex.getMessage());
            }
        });
    }
}
