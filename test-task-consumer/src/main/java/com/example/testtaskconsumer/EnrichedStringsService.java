package com.example.testtaskconsumer;

import com.example.testtaskconsumer.dto.EnrichedStringDto;
import com.example.testtaskconsumer.entity.EnrichedStrings;
import com.example.testtaskconsumer.repository.EnrichedStringsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
@Slf4j
public class EnrichedStringsService {

    private final EnrichedStringsRepository enrichedStringsRepository;

    public EnrichedStringDto saveEnrichedString(String str) {
        var entity = new EnrichedStrings(str, Instant.now());
        var result = enrichedStringsRepository.save(entity);
        log.info("Enriched string saved: {}", result);
        return EnrichedStringDto.fromEntity(result);
    }

}
