package com.example.testtaskconsumer.dto;

import com.example.testtaskconsumer.entity.EnrichedStrings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class EnrichedStringDto implements Serializable {

    private UUID id;

    private String value;

    private Instant createDate;

    public static EnrichedStringDto fromEntity(EnrichedStrings enrichedStrings) {
        return new EnrichedStringDto(enrichedStrings.getId(),
                enrichedStrings.getValue(),
                enrichedStrings.getCreateDate());
    }

}
