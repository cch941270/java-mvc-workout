package com.example.workout.workoutuser;

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
@RequestMapping("/api/workout-users")
public class WorkoutUserController {
    private final WorkoutUserRepository repository;
    private final WorkoutUserService service;

    public WorkoutUserController(WorkoutUserRepository repository, WorkoutUserService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/{id}")
    public WorkoutUserDto findById(@PathVariable Long id) {
        Optional<WorkoutUserDto> userDto = service.findById(id);
        if (userDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }
        return userDto.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody WorkoutUser user) {
        repository.save(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody WorkoutUser user, @PathVariable Integer id) {
        repository.save(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
