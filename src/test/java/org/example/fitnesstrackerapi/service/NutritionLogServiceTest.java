package org.example.fitnesstrackerapi.service;

import org.example.fitnesstrackerapi.exception.NutritionalLogNotFoundException;
import org.example.fitnesstrackerapi.model.entity.NutritionLog;
import org.example.fitnesstrackerapi.model.entity.User;
import org.example.fitnesstrackerapi.repository.NutritionLogRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.example.fitnesstrackerapi.model.enums.NutritionType.SNACK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NutritionLogServiceTest {
    private NutritionLogRepo nutritionLogRepo;
    private NutritionLogService nutritionLogService;

    @BeforeEach
    void setUp() {
        nutritionLogRepo = mock(NutritionLogRepo.class);
        nutritionLogService = new NutritionLogService(nutritionLogRepo);
    }

    @Test
    void testGetNutritionLogById_whenFound_returnsNutritionLog() {
        Long id = 1L;
        NutritionLog nutritionLog = new NutritionLog();
        when(nutritionLogRepo.findById(id)).thenReturn(Optional.of(nutritionLog));

        NutritionLog result = nutritionLogService.getNutritionLogById(id);

        assertNotNull(result);
        verify(nutritionLogRepo, times(1)).findById(id);
    }

    @Test
    void testGetNutritionLogById_whenNotFound_throwsException() {
        Long id = 99L;
        when(nutritionLogRepo.findById(id)).thenReturn(Optional.empty());

        NutritionalLogNotFoundException thrown = assertThrows(
                NutritionalLogNotFoundException.class,
                () -> nutritionLogService.getNutritionLogById(id)
        );

        assertEquals("Nutritional log not found with id 99", thrown.getMessage());
        verify(nutritionLogRepo, times(1)).findById(id);
    }

    @Test
    void shouldAddNutritionLog() throws ParseException {
        User user = new User();
        NutritionLog nutritionLog = new NutritionLog(
                user,
                SNACK,
                new SimpleDateFormat("yyyy-MM-dd").parse("2025-01-01"),
                "Protein shake",
                347,
                30,
                28,
                2);
        when(nutritionLogRepo.save(nutritionLog)).thenReturn(nutritionLog);

        NutritionLog result = nutritionLogService.createNutritionLog(nutritionLog);

        assertNotNull(result);
        assertEquals("Protein shake", result.getFood());
        verify(nutritionLogRepo, times(1)).save(nutritionLog);
    }

    @Test
    void shouldDeleteNutritionLog() {
        Long id = 1L;
        when(nutritionLogRepo.existsById(id)).thenReturn(true);
        nutritionLogService.deleteNutritionLogById(id);
        verify(nutritionLogRepo, times(1)).deleteById(id);
    }
}
