package com.example.testtaskconsumer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Entity
@Table(name = "enriched_strings")
public class EnrichedStrings implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    public EnrichedStrings(String value) {
        this.value = value;
    }

    public EnrichedStrings(String value, Instant createDate) {
        this.value = value;
        this.createDate = createDate;
    }
}
