package org.example.fitnesstrackerapi.service;

import org.example.fitnesstrackerapi.model.entity.Exercise;
import org.example.fitnesstrackerapi.repository.ExerciseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepo exerciseRepo;

    @Autowired
    public ExerciseService(ExerciseRepo exerciseRepo) {
        this.exerciseRepo = exerciseRepo;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepo.findAll();
    }

    public Exercise getExerciseById(Long id) {
        return exerciseRepo.findById(id).orElse(null);
    }

    public Exercise addExercise(Exercise exercise) {
        return exerciseRepo.save(exercise);
    }

    public Exercise updateExercise(Exercise exercise) {
        return exerciseRepo.save(exercise);
    }

    public void deleteExercise(Long id) {
        exerciseRepo.deleteById(id);
    }
}
