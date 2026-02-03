package com.example.workout.legexercise;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.workout.workoutuser.WorkoutUser;

@WebMvcTest(LegExerciseApiController.class)
@ActiveProfiles("test")
public class LegExerciseApiControllerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    LegExerciseRepository repository;

    @MockitoBean
    LegExerciseService service;

    private final WorkoutUser workoutUser = new WorkoutUser((long)1, "4mV3F@example.com", "password");
    private final List<LegExerciseDto> legExerciseDtos = new ArrayList<>();
    
    @BeforeEach
    void setUp() {
        legExerciseDtos.add(new LegExerciseDto(
            1,
            LegExerciseType.STEP_UP,
            LocalDateTime.now(),
            20,
            workoutUser.getEmail()
        ));
        legExerciseDtos.add(new LegExerciseDto(
            2,
            LegExerciseType.SQUAT,
            LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
            10,
            workoutUser.getEmail()
        ));
    }

    @Test
    void shouldFindAll() throws Exception {
        when(service.findAll()).thenReturn(legExerciseDtos);
        mvc.perform(get("/api/leg-exercises"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(legExerciseDtos.size())));
                
    }

    @Test
    void shouldFindById() throws Exception {
        LegExerciseDto legExerciseDto = legExerciseDtos.get(0);
        when(service.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(legExerciseDto));
        mvc.perform(get("/api/leg-exercises/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(legExerciseDto.id())))
                .andExpect(jsonPath("$.legExerciseType", is(legExerciseDto.legExerciseType().name())))
                .andExpect(jsonPath("$.count", is(legExerciseDto.count())));
    }

    @Test
    void shouldCreate() throws Exception {
        var legExercise = new LegExercise(
            null,
            LegExerciseType.LUNGE,
            LocalDateTime.now(),
            5,
            workoutUser
        );
        mvc.perform(post("/api/leg-exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(legExercise))
                    ).andExpect(status().isCreated());
    }

    @Test
    void shouldUpdate() throws Exception {
        var legExercise = new LegExercise(
            1,
            LegExerciseType.STEP_UP,
            LocalDateTime.now(),
            40,
            workoutUser
        );
        mvc.perform(put("/api/leg-exercises/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(legExercise))
                    ).andExpect(status().isNoContent());
    }

    @Test
    void shouldDelete() throws Exception {
        mvc.perform(delete("/api/leg-exercises/1"))
                .andExpect(status().isNoContent());
    }
}
