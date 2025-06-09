package org.example.fitnesstrackerapi.service;

import org.example.fitnesstrackerapi.exception.WorkoutNotFoundException;
import org.example.fitnesstrackerapi.model.entity.Workout;
import org.example.fitnesstrackerapi.repository.WorkoutRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
