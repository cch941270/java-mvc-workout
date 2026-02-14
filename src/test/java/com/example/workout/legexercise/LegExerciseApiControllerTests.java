package com.example.workout.legexercise;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.workout.role.Role;
import com.example.workout.role.RoleType;
import com.example.workout.workoutuser.WorkoutUser;
import com.example.workout.workoutuser.WorkoutUserRepository;

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
    WorkoutUserRepository workoutUserRepository;

    @MockitoBean
    LegExerciseApiService service;

    private final Role userRole = new Role((long)1, RoleType.USER);
    private final WorkoutUser workoutUser = new WorkoutUser(
        (long)1,
        "4mV3F@example.com",
        "password",
        Set.of(userRole)
    );
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
    @WithMockUser
    void shouldFindAll() throws Exception {
        when(service.findAll()).thenReturn(legExerciseDtos);
        mvc.perform(get("/api/leg-exercises"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(legExerciseDtos.size())));
                
    }

    @Test
    @WithMockUser
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
    @WithMockUser
    void shouldCreate() throws Exception {
        when(workoutUserRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(Optional.of(workoutUser));
        var legExerciseDto = new LegExerciseDto(
            null,
            LegExerciseType.LUNGE,
            LocalDateTime.now(),
            5,
            workoutUser.getEmail()
        );
        mvc.perform(post("/api/leg-exercises")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(legExerciseDto))
                    ).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void shouldUpdate() throws Exception {
        when(workoutUserRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(Optional.of(workoutUser));
        var legExerciseDto = new LegExerciseDto(
            1,
            LegExerciseType.STEP_UP,
            LocalDateTime.now(),
            40,
            workoutUser.getEmail()
        );
        mvc.perform(put("/api/leg-exercises/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(legExerciseDto))
                    ).andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    void shouldDelete() throws Exception {
        mvc.perform(delete("/api/leg-exercises/1")
                .with(csrf()))
                .andExpect(status().isNoContent());
    }
}
