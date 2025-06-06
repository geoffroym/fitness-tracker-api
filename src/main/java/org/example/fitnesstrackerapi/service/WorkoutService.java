package org.example.fitnesstrackerapi.service;

import org.example.fitnesstrackerapi.exception.ExerciseNotFoundException;
import org.example.fitnesstrackerapi.exception.WorkoutNotFoundException;
import org.example.fitnesstrackerapi.model.entity.Workout;
import org.example.fitnesstrackerapi.repository.WorkoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WorkoutService {

    private final WorkoutRepo workoutRepo;

    @Autowired
    public WorkoutService(WorkoutRepo workoutRepo) {
        this.workoutRepo = workoutRepo;
    }

    public Workout getWorkoutById(Long id) {
        return workoutRepo.findById(id).orElseThrow(() -> new WorkoutNotFoundException(id));
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepo.findAll();
    }

    public Workout createWorkout(Workout workout) {
        return workoutRepo.save(workout);
    }

    public void deleteWorkout(Long id) {
        workoutRepo.deleteById(id);
    }
}
