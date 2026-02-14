package com.example.workout.legexercise;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.workout.role.Role;
import com.example.workout.role.RoleType;
import com.example.workout.workoutuser.WorkoutUser;
import com.example.workout.workoutuser.WorkoutUserDetailsService;

@WebMvcTest(LegExerciseController.class)
@ActiveProfiles("test")
public class LegExerciseControllerTests {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    LegExerciseRepository repository;

    @MockitoBean
    LegExerciseService service;

    @MockitoBean
    WorkoutUserDetailsService userDetailsService;

    private final Role userRole = new Role((long)1, RoleType.USER);
    private final WorkoutUser workoutUser = new WorkoutUser((long)1, "test@example.com", "test", "password", Set.of(userRole));
    private final List<LegExercise> legExercises = new ArrayList<>();
    private final LegExerciseType[] legExerciseTypes = LegExerciseType.values();

    @BeforeEach
    void setUp() {
        legExercises.add(new LegExercise(
            1,
            LegExerciseType.STEP_UP,
            LocalDateTime.now(),
            20,
            workoutUser
        ));
        legExercises.add(new LegExercise(
            2,
            LegExerciseType.SQUAT,
            LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
            10,
            workoutUser
        ));
    }

    @Test
    @WithMockUser
    void shouldReturnLegExercisesIndex() throws Exception {
        when(repository.findAll()).thenReturn(legExercises);
        mvc.perform(get("/leg-exercises"))
                .andExpect(status().isOk())
                .andExpect(view().name("legexercises/index"))
                .andExpect(model().attributeExists("legExercises"));
    }

    @Test
    @WithMockUser
    void shouldReturnLegExerciseShow() throws Exception {
        when(service.findByUsernameAndId(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(legExercises.get(0)));
        mvc.perform(get("/leg-exercises/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("legexercises/show"))
                .andExpect(model().attributeExists("legExercise"));
    }

    @Test
    @WithMockUser
    void shouldRedirectWhenLegExerciseNotFound() throws Exception {
        when(service.findByUsernameAndId(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());
        mvc.perform(get("/leg-exercises/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/leg-exercises"));
    }

    @Test
    @WithMockUser
    void shouldReturnNewForm() throws Exception {
        mvc.perform(get("/leg-exercises/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("legexercises/new"))
                .andExpect(model().attributeExists("legExercise"))
                .andExpect(model().attribute("allLegExerciseTypes", legExerciseTypes));
    }

    @Test
    @WithMockUser
    void shouldCreateLegExercise() throws Exception {
        mvc.perform(post("/leg-exercises")
                        .with(csrf())
                        .param("legExerciseType", "LUNGE")
                        .param("count", "5")
                        .param("createdAt", LocalDateTime.now().toString())
                    )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/leg-exercises"));
    }

    @Test
    @WithMockUser
    void shouldReturnEditForm() throws Exception {
        when(service.findByUsernameAndId(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(legExercises.get(0)));
        mvc.perform(get("/leg-exercises/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("legexercises/edit"))
                .andExpect(model().attributeExists("legExercise"))
                .andExpect(model().attribute("allLegExerciseTypes", legExerciseTypes));
    }

    @Test
    @WithMockUser
    void shouldUpdateLegExercise() throws Exception {
        when(service.findByUsernameAndId(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(legExercises.get(0)));
        mvc.perform(put("/leg-exercises/1")
                        .with(csrf())
                        .param("legExerciseType", "STEP_UP")
                        .param("count", "40")
                    )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/leg-exercises/1"));
    }

    @Test
    @WithMockUser
    void shouldDeleteLegExercise() throws Exception {
        when(service.findByUsernameAndId(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(legExercises.get(0)));
        mvc.perform(delete("/leg-exercises/1")
                        .with(csrf())
                    )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/leg-exercises"));
    }
}