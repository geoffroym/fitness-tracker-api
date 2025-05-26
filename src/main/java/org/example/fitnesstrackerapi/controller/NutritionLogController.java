package org.example.fitnesstrackerapi.controller;

import org.example.fitnesstrackerapi.model.entity.NutritionLog;
import org.example.fitnesstrackerapi.service.NutritionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutritionlog")
public class NutritionLogController {

    private final NutritionLogService nutritionLogService;

    @Autowired
    public NutritionLogController(NutritionLogService nutritionLogService) {
        this.nutritionLogService = nutritionLogService;
    }

    @GetMapping
    public ResponseEntity<List<NutritionLog>> getNutritionLogs() {
        return ResponseEntity.ok(nutritionLogService.getAllNutritionLogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NutritionLog> getNutritionLogById(@PathVariable Long id) {
        return ResponseEntity.ok(nutritionLogService.getNutritionLogById(id));
    }

    @PostMapping
    public ResponseEntity<NutritionLog> addNutritionalLog(@RequestBody NutritionLog nutritionLog) {
        return ResponseEntity.ok(nutritionLogService.createNutritionLog(nutritionLog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutritionLogById(@PathVariable Long id) {
        nutritionLogService.deleteNutritionLogById(id);
        return ResponseEntity.noContent().build();
    }
}
