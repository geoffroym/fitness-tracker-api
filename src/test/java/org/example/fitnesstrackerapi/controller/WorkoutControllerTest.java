package org.example.fitnesstrackerapi.controller;

import org.example.fitnesstrackerapi.dto.WorkoutDto;
import org.example.fitnesstrackerapi.model.entity.User;
import org.example.fitnesstrackerapi.model.entity.Workout;
import org.example.fitnesstrackerapi.service.UserService;
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
import java.util.Date;
import java.util.List;

import static org.example.fitnesstrackerapi.model.enums.Goal.HYPERTROPHY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WorkoutController.class)
public class WorkoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserService userService; // Autowire UserService

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public WorkoutService workoutService() {
            return Mockito.mock(WorkoutService.class);
        }
        @Bean
        UserService userService(){
            return Mockito.mock(UserService.class);
        }

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }
    }

    @Test
    void getAllWorkouts_shouldReturnListOfWorkouts() throws Exception {
        Workout workout = new Workout();
        User user = new User("Cami", "cami@gmail.com", "1234", new SimpleDateFormat("yyyy-MM-dd").parse("1996-01-01"), 172, 62, HYPERTROPHY);
        user.setUserId(2L);
        workout.setWorkoutId(1L);
        workout.setUser_id(user);
        workout.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2025-01-01"));
        workout.setDurationMinutes(100);

        Mockito.when(workoutService.getAllWorkouts()).thenReturn(List.of(workout));

        mockMvc.perform(get("/api/workout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].workoutId").value(1))
                .andExpect(jsonPath("$[0].durationMinutes").value(100))
                .andExpect(jsonPath("$[0].user_id.name").value("Cami"))
                .andExpect(jsonPath("$[0].user_id.email").value("cami@gmail.com"));
    }

    @Test
    void getWorkoutById_shouldReturnWorkout() throws Exception {
        Long workoutId = 1L;
        Workout workout = new Workout();
        User user = new User("Cami", "cami@gmail.com", "1234", new SimpleDateFormat("yyyy-MM-dd").parse("1996-01-01"), 172, 62, HYPERTROPHY);
        user.setUserId(2L);
        workout.setWorkoutId(workoutId);
        workout.setUser_id(user);
        workout.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2025-01-01"));
        workout.setDurationMinutes(100);

        Mockito.when(workoutService.getWorkoutById(workoutId)).thenReturn(workout);

        mockMvc.perform(get("/api/workout/{id}", workoutId))
                .andExpect(status().isOk())
                .andExpect(content().string(workout.toString())); // Assuming toString() provides the expected string representation
    }

    @Test
    void createWorkout_shouldReturnCreatedWorkout() throws Exception {
        // Prepare data
        Long userId = 2L;
        Date workoutDate = new SimpleDateFormat("yyyy-MM-dd").parse("2025-01-01");
        int durationMinutes = 100;

        User user = new User("Cami", "cami@gmail.com", "1234", new SimpleDateFormat("yyyy-MM-dd").parse("1996-01-01"), 172, 62, HYPERTROPHY);
        user.setUserId(userId);

        WorkoutDto workoutDto = new WorkoutDto();
        workoutDto.setUser_id(userId);
        workoutDto.setDate(workoutDate);
        workoutDto.setDurationMinutes(durationMinutes);

        Workout createdWorkout = new Workout(user, workoutDate, durationMinutes);
        createdWorkout.setWorkoutId(1L);

        // mock service calls
        Mockito.when(userService.getUserById(userId)).thenReturn(user);
        Mockito.when(workoutService.createWorkout(Mockito.any(Workout.class))).thenReturn(createdWorkout);

        // POST request
        mockMvc.perform(post("/api/workout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(workoutDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.workoutId").value(1))
                .andExpect(jsonPath("$.durationMinutes").value(durationMinutes))
                .andExpect(jsonPath("$.user_id.name").value("Cami"));
    }

    @Test
    void deleteWorkout_shouldReturnNoContent() throws Exception {
        Long workoutId = 1L;

        Mockito.doNothing().when(workoutService).deleteWorkout(workoutId);

        mockMvc.perform(delete("/api/workout/{id}", workoutId))
                .andExpect(status().isNoContent());

        Mockito.verify(workoutService, Mockito.times(1)).deleteWorkout(workoutId);
    }
}
