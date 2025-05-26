package org.example.fitnesstrackerapi.service;

import org.example.fitnesstrackerapi.exceptions.UserNotFoundException;
import org.example.fitnesstrackerapi.model.entity.User;
import org.example.fitnesstrackerapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        User user;
        try {
            user = userRepo.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException("User " + id + " not found.");
        }
        return user;
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
