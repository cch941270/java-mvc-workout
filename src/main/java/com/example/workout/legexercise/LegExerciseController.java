package com.example.workout.legexercise;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/leg-exercises")
public class LegExerciseController {
    private final LegExerciseRepository legExerciseRepository;

    public LegExerciseController(LegExerciseRepository legExerciseRepository) {
        this.legExerciseRepository = legExerciseRepository;
    }

    @GetMapping("")
    List<LegExercise> findAll() {
        return legExerciseRepository.findAll();
    }

    @GetMapping("/{id}")
    LegExercise findById(@PathVariable Integer id) {
        Optional<LegExercise> legExercise = legExerciseRepository.findById(id);
        if (legExercise.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Leg exercise not found.");
        }
        return legExercise.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody LegExercise legExercise) {
        legExerciseRepository.save(legExercise);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody LegExercise legExercise, @PathVariable Integer id) {
        legExerciseRepository.save(legExercise);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        legExerciseRepository.delete(legExerciseRepository.findById(id).get());
    }
}
