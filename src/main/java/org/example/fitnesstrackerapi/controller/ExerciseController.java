package org.example.fitnesstrackerapi.controller;

import org.example.fitnesstrackerapi.dto.ExerciseDto;
import org.example.fitnesstrackerapi.model.entity.Exercise;
import org.example.fitnesstrackerapi.model.entity.Workout;
import org.example.fitnesstrackerapi.service.ExerciseService;
import org.example.fitnesstrackerapi.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final WorkoutService workoutService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, WorkoutService workoutService) {
        this.exerciseService = exerciseService;
        this.workoutService = workoutService;
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getExercises() {
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.getExerciseById(id);
        return ResponseEntity.ok(exercise.toString());
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody ExerciseDto exerciseDto) {
        Workout workout = workoutService.getWorkoutById(exerciseDto.getWorkout_id());
        Exercise exercise = new Exercise(
                workout,
                exerciseDto.getExercise_name(),
                exerciseDto.getSets(),
                exerciseDto.getReps(),
                exerciseDto.getWeightKg(),
                exerciseDto.getType()
                );
        return ResponseEntity.ok(exerciseService.addExercise(exercise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

}
