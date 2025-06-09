package org.example.fitnesstrackerapi.service;

import org.example.fitnesstrackerapi.exception.NutritionalLogNotFoundException;
import org.example.fitnesstrackerapi.model.entity.NutritionLog;
import org.example.fitnesstrackerapi.repository.NutritionLogRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
}
