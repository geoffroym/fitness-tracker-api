package org.example.fitnesstrackerapi.service;

import org.example.fitnesstrackerapi.exception.NutritionalLogNotFoundException;
import org.example.fitnesstrackerapi.model.entity.NutritionLog;
import org.example.fitnesstrackerapi.repository.NutritionLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionLogService {

    private final NutritionLogRepo nutritionLogRepo;

    @Autowired
    public NutritionLogService(NutritionLogRepo nutritionLogRepo) {
        this.nutritionLogRepo = nutritionLogRepo;
    }

    public List<NutritionLog> getAllNutritionLogs() {
        return nutritionLogRepo.findAll();
    }

    public NutritionLog getNutritionLogById(Long id) {
        return nutritionLogRepo.findById(id).orElseThrow(() -> new NutritionalLogNotFoundException(id));
    }

    public NutritionLog createNutritionLog(NutritionLog nutritionLog) {
        return nutritionLogRepo.save(nutritionLog);
    }

    public void deleteNutritionLogById(Long id) {
        nutritionLogRepo.deleteById(id);
    }
}
