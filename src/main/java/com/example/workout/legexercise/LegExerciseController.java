package com.example.workout.legexercise;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@Controller
// @RequestMapping("/api/leg-exercises")
public class LegExerciseController {
    private final LegExerciseRepository legExerciseRepository;
    private final LegExerciseService legExerciseService;

    public LegExerciseController(LegExerciseRepository legExerciseRepository, LegExerciseService legExerciseService) {
        this.legExerciseRepository = legExerciseRepository;
        this.legExerciseService = legExerciseService;
    }

    @GetMapping({"", "/"})
    public String indexPage(Model model) {
        model.addAttribute("allLegExercises", legExerciseService.findAll());
        return "legexercise/index";
    }

    @GetMapping({"/{id}", "/{id}/"})
    LegExerciseDto findById(@PathVariable Integer id) {
        Optional<LegExerciseDto> legExerciseDto = legExerciseService.findById(id);
        if (legExerciseDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Leg exercise not found.");
        }
        return legExerciseDto.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping({"", "/"})
    void create(@Valid @RequestBody LegExercise legExercise) {
        legExerciseRepository.save(legExercise);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping({"/{id}", "/{id}/"})
    void update(@Valid @RequestBody LegExercise legExercise, @PathVariable Integer id) {
        legExerciseRepository.save(legExercise);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"/{id}", "/{id}/"})
    void delete(@PathVariable Integer id) {
        legExerciseRepository.deleteById(id);
    }
}
