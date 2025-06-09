package org.example.fitnesstrackerapi.controller;

import org.example.fitnesstrackerapi.model.entity.Exercise;
import org.example.fitnesstrackerapi.service.ExerciseService;
import org.example.fitnesstrackerapi.service.WorkoutService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExerciseController.class)
public class ExerciseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExerciseService exerciseService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public ExerciseService exerciseService() {
            return Mockito.mock(ExerciseService.class);
        }

        @Bean
        public WorkoutService workoutService() {
            return Mockito.mock(WorkoutService.class);
        }
    }

    @Test
    void getExercises_shouldReturnListOfExercises() throws Exception {
        Exercise exercise = new Exercise();
        exercise.setExerciseId(1L);
        exercise.setExerciseName("Pushup");

        Mockito.when(exerciseService.getAllExercises()).thenReturn(List.of(exercise));

        mockMvc.perform(get("/api/exercise"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].exerciseName").value("Pushup"));
    }
}
