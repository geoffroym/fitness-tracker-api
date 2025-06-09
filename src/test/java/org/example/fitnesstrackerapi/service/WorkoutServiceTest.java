package org.example.fitnesstrackerapi.service;

import org.example.fitnesstrackerapi.exception.WorkoutNotFoundException;
import org.example.fitnesstrackerapi.model.entity.User;
import org.example.fitnesstrackerapi.model.entity.Workout;
import org.example.fitnesstrackerapi.repository.WorkoutRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WorkoutServiceTest {
    private WorkoutRepo workoutRepo;
    private WorkoutService workoutService;

    @BeforeEach
    void setUp() {
        workoutRepo = mock(WorkoutRepo.class);
        workoutService = new WorkoutService(workoutRepo);
    }

    @Test
    void testGetWorkoutById_whenFound_returnWorkout(){
        Long id = 1L;
        Workout workout = new Workout();
        when(workoutRepo.findById(id)).thenReturn(Optional.of(workout));

        Workout workoutFound = workoutService.getWorkoutById(id);

        assertNotNull(workoutFound);
        verify(workoutRepo, times(1)).findById(id);
    }

    @Test
    void testGetWorkoutById_whenNotFound_throwsException(){
        Long id = 99L;
        when(workoutRepo.findById(id)).thenReturn(Optional.empty());

        WorkoutNotFoundException thrown = assertThrows(WorkoutNotFoundException.class, () -> workoutService.getWorkoutById(id));

        assertEquals("Workout not found with id 99", thrown.getMessage());
        verify(workoutRepo, times(1)).findById(id);
    }

    @Test
    void shouldAddWorkoutSuccessfully() throws ParseException {
        User user = new User();
        Workout workout = new Workout(user, new SimpleDateFormat("yyyy-MM-dd").parse("2025-01-01"), 60);
        when(workoutRepo.save(workout)).thenReturn(workout);

        Workout result = workoutService.createWorkout(workout);

        assertNotNull(result);
        assertEquals(workout, result);
        verify(workoutRepo, times(1)).save(workout);
    }

    @Test
    void shouldDeleteWorkoutSuccessfully() {
        Long id = 1L;
        when(workoutRepo.existsById(id)).thenReturn(true);

        workoutService.deleteWorkout(id);
        verify(workoutRepo, times(1)).deleteById(id);
    }
}
