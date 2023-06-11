package com.example.testtaskproducer.service;

import com.example.testtaskproducer.dto.StringDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StringSenderScheduler {

    private final StringGenerator stringGenerator;
    private final KafkaProducer kafkaProducer;

    @Value("${kafka-topic.strings-under-100}")
    private String stringsUnder100TopicName;

    @Scheduled(fixedDelayString = "${sheduled.strings-under-100}")
    public void sendString() {
        log.info("PROFILER: Start messaging: {}", System.currentTimeMillis());
        var strToSend = stringGenerator.getRandomStrUnder100();
        kafkaProducer.send(stringsUnder100TopicName, new StringDto(strToSend));
    }
}
