package org.example.fitnesstrackerapi.exception;

public abstract class BaseNotFoundException extends RuntimeException {
    public BaseNotFoundException(String message) {
        super(message);
    }
}

