package org.example.fitnesstrackerapi.service;

import org.example.fitnesstrackerapi.exception.ExerciseNotFoundException;
import org.example.fitnesstrackerapi.model.entity.Exercise;
import org.example.fitnesstrackerapi.model.entity.Workout;
import org.example.fitnesstrackerapi.repository.ExerciseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.example.fitnesstrackerapi.model.enums.ExerciseType.STRENGTH;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ExerciseServiceTest {

    private ExerciseRepo exerciseRepo;
    private ExerciseService exerciseService;

    @BeforeEach
    void setUp() {
        exerciseRepo = mock(ExerciseRepo.class); // fake JPA repo
        exerciseService = new ExerciseService(exerciseRepo);
    }

    @Test
    void testGetExerciseById_whenFound_returnsExercise() {
        // given
        Long id = 1L;
        Exercise exercise = new Exercise();
        when(exerciseRepo.findById(id)).thenReturn(Optional.of(exercise));

        // when
        Exercise result = exerciseService.getExerciseById(id);

        // then
        assertNotNull(result);
        verify(exerciseRepo, times(1)).findById(id);
    }

    @Test
    void testGetExerciseById_whenNotFound_throwsException() {
        // given
        Long id = 99L;
        when(exerciseRepo.findById(id)).thenReturn(Optional.empty());

        // then
        ExerciseNotFoundException thrown = assertThrows(
                ExerciseNotFoundException.class,
                () -> exerciseService.getExerciseById(id)
        );

        assertEquals("Exercise not found with id 99", thrown.getMessage());
        verify(exerciseRepo, times(1)).findById(id);
    }

    @Test
    void shouldAddExerciseSuccessfully() {
        Workout workout = new Workout();
        Exercise exercise = new Exercise(workout, "Hip-thrusts", 3, 10, 105.0, STRENGTH);
        when(exerciseRepo.save(exercise)).thenReturn(exercise);

        Exercise result = exerciseService.addExercise(exercise);

        assertNotNull(result);
        assertEquals("Hip-thrusts", result.getExerciseName());
        verify(exerciseRepo, times(1)).save(exercise);
    }

    @Test
    void shouldDeleteExerciseSuccessfully() {
        Long exerciseId = 1L;
        when(exerciseRepo.existsById(exerciseId)).thenReturn(true);

        exerciseService.deleteExercise(exerciseId);

        verify(exerciseRepo, times(1)).deleteById(exerciseId);

    }
}
