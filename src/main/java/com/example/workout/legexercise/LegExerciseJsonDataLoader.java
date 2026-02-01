package com.example.workout.legexercise;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

// @Component
public class LegExerciseJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LegExerciseJsonDataLoader.class);
    private final LegExerciseRepository repository;
    private final ObjectMapper objectMapper;

    public LegExerciseJsonDataLoader(LegExerciseRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/exercises.json")) {
                LegExercises allLegExercises = objectMapper.readValue(inputStream, LegExercises.class);
                System.out.println(allLegExercises);
                log.info("Reading {} exercises from JSON data and saving to database.", allLegExercises.legExercises().size());
                repository.saveAll(allLegExercises.legExercises());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading exercises from JSON data because the collection contains data.");
        }
    }
}
