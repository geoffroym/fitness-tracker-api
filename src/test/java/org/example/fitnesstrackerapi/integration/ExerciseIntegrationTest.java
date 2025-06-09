package org.example.fitnesstrackerapi.integration;

import org.example.fitnesstrackerapi.model.entity.Exercise;
import org.example.fitnesstrackerapi.repository.ExerciseRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class ExerciseIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0")
            .withDatabaseName("fitnesstracker")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private ExerciseRepo exerciseRepo;

    @Test
    void shouldSaveAndFetchExerciseFromDatabase() {
        Exercise exercise = new Exercise();
        exercise.setExerciseName("Deadlift");
        exercise.setReps(5);
        exercise.setSets(3);
        exercise.setWeightKg(100.0);

        exerciseRepo.save(exercise);

        List<Exercise> exercises = exerciseRepo.findAll();
        assertThat(exercises).extracting(Exercise::getExerciseName).contains("Deadlift");
    }
}
