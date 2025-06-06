package org.example.fitnesstrackerapi.exception;

public class NutritionalLogNotFoundException extends BaseNotFoundException {
    public NutritionalLogNotFoundException(Long id) {
        super("Nutritional log not found with id " + id);
    }
}

