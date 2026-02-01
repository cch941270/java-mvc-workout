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

@WebMvcTest(LegExerciseController.class)
@ActiveProfiles("test")
public class LegExerciseControllerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    LegExerciseRepository repository;

    private final List<LegExercise> legExercises = new ArrayList<>();
    
    @BeforeEach
    void setUp() {
        legExercises.add(new LegExercise(
            1,
            LegExerciseType.STEP_UP,
            LocalDateTime.now(),
            20
        ));
        legExercises.add(new LegExercise(
            2,
            LegExerciseType.SQUAT,
            LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
            10
        ));
    }

    @Test
    void shouldFindAll() throws Exception {
        when(repository.findAll()).thenReturn(legExercises);
        mvc.perform(get("/api/leg-exercises"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(legExercises.size())));
                
    }

    @Test
    void shouldFindById() throws Exception {
        LegExercise legExercise = legExercises.get(0);
        when(repository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(legExercise));
        mvc.perform(get("/api/leg-exercises/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(legExercise.getId())))
                .andExpect(jsonPath("$.legExerciseType", is(legExercise.getLegExerciseType().name())))
                .andExpect(jsonPath("$.count", is(legExercise.getCount())));
    }

    @Test
    void shouldCreate() throws Exception {
        var legExercise = new LegExercise(
            null,
            LegExerciseType.LUNGE,
            LocalDateTime.now(),
            5
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
            40
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
