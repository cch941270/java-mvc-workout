package com.example.workout.workoutuser;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WorkoutUserController.class)
@ActiveProfiles("test")
public class WorkoutUserControllerTests {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    WorkoutUserService service;

    @MockitoBean
    WorkoutUserDetailsService userDetailsService;

    private final WorkoutUser workoutUser = new WorkoutUser("test@example.com", "testuser", "password");
    private final WorkoutUserDto workoutUserDto = new WorkoutUserDto(
        1L,
        "test@example.com",
        "testuser",
        List.of()
    );

    @BeforeEach
    void setUp() {
        when(service.findByUsername(ArgumentMatchers.anyString())).thenReturn(Optional.of(workoutUser));
        when(service.findDtoByUsername(ArgumentMatchers.anyString())).thenReturn(Optional.of(workoutUserDto));
    }

    @Test
    @WithMockUser
    void shouldReturnWorkoutUserShow() throws Exception {
        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("workoutusers/show"))
                .andExpect(model().attributeExists("workoutUser"));
    }

    @Test
    @WithMockUser
    void shouldReturnNewForm() throws Exception {
        mvc.perform(get("/users/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("workoutusers/new"))
                .andExpect(model().attributeExists("workoutUser"));
    }

    @Test
    @WithMockUser
    void shouldCreateWorkoutUser() throws Exception {
        mvc.perform(post("/users")
                        .with(csrf())
                        .param("email", "newuser@example.com")
                        .param("password", "password")
                    )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    @WithMockUser
    void shouldReturnEditForm() throws Exception {
        mvc.perform(get("/users/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("workoutusers/edit"))
                .andExpect(model().attributeExists("workoutUser"));
    }

    @Test
    @WithMockUser
    void shouldUpdateWorkoutUser() throws Exception {
        mvc.perform(put("/users")
                        .with(csrf())
                        .param("id", "1")
                        .param("email", "updated@example.com")
                        .param("password", "newpassword")
                    )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @Test
    @WithMockUser
    void shouldDeleteWorkoutUser() throws Exception {
        mvc.perform(delete("/users")
                        .with(csrf())
                    )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}