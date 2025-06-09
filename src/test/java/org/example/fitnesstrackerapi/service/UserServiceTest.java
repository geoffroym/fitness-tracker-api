package org.example.fitnesstrackerapi.service;

import org.example.fitnesstrackerapi.exception.UserNotFoundException;
import org.example.fitnesstrackerapi.model.entity.User;
import org.example.fitnesstrackerapi.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserRepo userRepo;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepo = mock(UserRepo.class);
        userService = new UserService(userRepo);
    }

    @Test
    void testGetUserById_whenFound_returnsUser() {

        Long id = 1L;
        User user = new User();
        when(userRepo.findById(id)).thenReturn(Optional.of(user));


        User result = userService.getUserById(id);


        assertNotNull(result);
        verify(userRepo, times(1)).findById(id);
    }

    @Test
    void testGetUserById_whenNotFound_throwsException() {
        Long id = 99L;
        when(userRepo.findById(id)).thenReturn(Optional.empty());

        UserNotFoundException thrown = assertThrows(
                UserNotFoundException.class,
                () -> userService.getUserById(id)
        );

        assertEquals("User not found with id 99", thrown.getMessage());
        verify(userRepo, times(1)).findById(id);
    }
}
