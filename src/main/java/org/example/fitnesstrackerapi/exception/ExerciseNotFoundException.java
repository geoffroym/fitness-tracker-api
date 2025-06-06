package org.example.fitnesstrackerapi.exception;

public class ExerciseNotFoundException extends BaseNotFoundException{
    public ExerciseNotFoundException(Long id) {
        super("Exercise not found with id " + id);
    }
}
