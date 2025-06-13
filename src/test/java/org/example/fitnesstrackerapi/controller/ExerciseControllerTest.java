package org.example.fitnesstrackerapi.controller;

import org.example.fitnesstrackerapi.model.entity.Exercise;
import org.example.fitnesstrackerapi.model.entity.User;
import org.example.fitnesstrackerapi.model.entity.Workout;
import org.example.fitnesstrackerapi.service.ExerciseService;
import org.example.fitnesstrackerapi.service.WorkoutService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.example.fitnesstrackerapi.model.enums.ExerciseType.STRENGTH;
import static org.example.fitnesstrackerapi.model.enums.Goal.HYPERTROPHY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
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

    @Test
    void getExerciseById_shouldReturnExercise() throws Exception {
        Long exerciseId = 1L;
        User user = new User("Cami", "cami@gmail.com", "1234", new SimpleDateFormat("yyyy-MM-dd").parse("1996-01-01"), 172, 62, HYPERTROPHY);
        Workout workout = new Workout(user, new SimpleDateFormat("yyyy-MM-dd").parse("2025-02-01"), 100);
        Exercise exercise = new Exercise(workout, "Pushup", 3, 10, 100, STRENGTH);
        exercise.setExerciseId(exerciseId);

        Mockito.when(exerciseService.getExerciseById(exerciseId)).thenReturn(exercise);

        mockMvc.perform(get("/api/exercise/{id}", exerciseId))
                .andExpect(status().isOk())
                .andExpect(content().string(exercise.toString()));
    }

    @Test
    void deleteWorkout_shouldReturnNoContent() throws Exception {
        Long exerciseId = 1L;

        Mockito.doNothing().when(exerciseService).deleteExercise(exerciseId);

        mockMvc.perform(delete("/api/exercise/{id}", exerciseId))
                .andExpect(status().isNoContent());

        Mockito.verify(exerciseService, Mockito.times(1)).deleteExercise(exerciseId);
    }
}
