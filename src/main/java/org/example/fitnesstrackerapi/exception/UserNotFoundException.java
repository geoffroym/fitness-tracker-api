package org.example.fitnesstrackerapi.exception;

public class UserNotFoundException extends BaseNotFoundException {
    public UserNotFoundException(Long id) {
        super("User not found with id " + id);
    }
}

