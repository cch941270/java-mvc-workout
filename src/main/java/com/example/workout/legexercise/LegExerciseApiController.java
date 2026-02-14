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
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/leg-exercises")
public class LegExerciseApiController {
    private final LegExerciseApiService service;

    @GetMapping("")
    List<LegExerciseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    LegExerciseDto findById(@PathVariable Integer id) {
        Optional<LegExerciseDto> legExerciseDto = service.findById(id);
        if (legExerciseDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Leg exercise not found.");
        }
        return legExerciseDto.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody LegExerciseDto legExerciseDto) {
        service.saveLegExercise(legExerciseDto, null);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody LegExerciseDto legExerciseDto, @PathVariable Integer id) {
        service.saveLegExercise(legExerciseDto, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
