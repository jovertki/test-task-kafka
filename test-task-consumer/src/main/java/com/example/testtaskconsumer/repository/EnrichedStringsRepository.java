package com.example.testtaskconsumer.repository;

import com.example.testtaskconsumer.entity.EnrichedStrings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnrichedStringsRepository extends JpaRepository<EnrichedStrings, UUID> {

}