package org.example.fitnesstrackerapi.controller;

import org.example.fitnesstrackerapi.model.entity.Exercise;
import org.example.fitnesstrackerapi.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.security.AuthenticationAuditListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise")
public class ExcerciseController {
    private final ExerciseService exerciseService;
    private final AuthenticationAuditListener authenticationAuditListener;

    @Autowired
    public ExcerciseController(ExerciseService exerciseService, AuthenticationAuditListener authenticationAuditListener) {
        this.exerciseService = exerciseService;
        this.authenticationAuditListener = authenticationAuditListener;
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
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        return ResponseEntity.ok(exerciseService.addExercise(exercise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

}
