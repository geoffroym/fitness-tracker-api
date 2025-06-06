package org.example.fitnesstrackerapi.exception;

public class WorkoutNotFoundException extends BaseNotFoundException {
    public WorkoutNotFoundException(Long id) {
        super("Workout not found with id " + id);
    }
}
