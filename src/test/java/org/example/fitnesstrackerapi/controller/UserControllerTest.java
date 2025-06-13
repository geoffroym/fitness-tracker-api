package org.example.fitnesstrackerapi.controller;

import org.example.fitnesstrackerapi.model.entity.User;
import org.example.fitnesstrackerapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.example.fitnesstrackerapi.model.enums.Goal.HYPERTROPHY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public UserService userService() {
            return Mockito.mock(UserService.class);
        }

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }
    }

    @Test
    void getUsers_shouldReturnListOfUsers() throws Exception {
        User user = new User("Cami", "cami@gmail.com", "1234", new SimpleDateFormat("yyyy-MM-dd").parse("1996-01-01"), 172, 62, HYPERTROPHY);

        Mockito.when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Cami"));

    }

    @Test
    void getUserById_shouldReturnUser() throws Exception {
        Long userId = 1L;
        User user = new User("Cami", "cami@gmail.com", "1234", new SimpleDateFormat("yyyy-MM-dd").parse("1996-01-01"), 172, 62, HYPERTROPHY);
        user.setUserId(userId);

        Mockito.when(userService.getUserById(userId)).thenReturn(user);
        mockMvc.perform(get("/api/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.name").value("Cami"))
                .andExpect(jsonPath("$.email").value("cami@gmail.com"))
                .andExpect(jsonPath("$.height").value(172.0))
                .andExpect(jsonPath("$.weight").value(62.0))
                .andExpect(jsonPath("$.goal").value("HYPERTROPHY"));
    }

    @Test
    void createUser_shouldCreateUser() throws Exception {
        Long userId = 1L;
        User user = new User("Cami", "cami@gmail.com", "1234", new SimpleDateFormat("yyyy-MM-dd").parse("1996-01-01"), 172, 62, HYPERTROPHY);
        user.setUserId(userId);

        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }


    @Test
    void deleteUser_shouldDeleteUser() throws Exception {
        Long userId = 1L;

        Mockito.doNothing().when(userService).deleteUser(userId);

        mockMvc.perform(delete("/api/user/{userId}", userId))
                .andExpect(status().isNoContent());

        Mockito.verify(userService, Mockito.times(1)).deleteUser(userId);
    }

}
