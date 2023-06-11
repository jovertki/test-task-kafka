package com.example.testtaskproducer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StringGenerator {

    private static final int MAX_STRING_SIZE = 101;
    private static final int MIN_STRING_SIZE = 1;

    public String getRandomStrUnder100() {
        var generatedString = RandomStringUtils.randomAlphabetic(MIN_STRING_SIZE, MAX_STRING_SIZE);
        log.debug("Generated str: {}", generatedString);
        return generatedString;
    }

}
