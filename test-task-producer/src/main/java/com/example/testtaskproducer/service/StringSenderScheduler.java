package com.example.testtaskproducer.service;

import com.example.testtaskproducer.dto.StringDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        log.info("PROFILER: Start messaging: {}", LocalDateTime.now());
        var strToSend = stringGenerator.getRandomStrUnder100();
        log.info("PROFILER: Sending string: {}, time: {}", strToSend, LocalDateTime.now());
        kafkaProducer.send(stringsUnder100TopicName, new StringDto(strToSend));
    }
}
