package org.example.fitnesstrackerapi.controller;

import org.example.fitnesstrackerapi.dto.NutritionLogDto;
import org.example.fitnesstrackerapi.model.entity.NutritionLog;
import org.example.fitnesstrackerapi.model.entity.User;
import org.example.fitnesstrackerapi.service.NutritionLogService;
import org.example.fitnesstrackerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutritionlog")
public class NutritionLogController {

    private final NutritionLogService nutritionLogService;
    private final UserService userService;

    @Autowired
    public NutritionLogController(NutritionLogService nutritionLogService, UserService userService) {
        this.nutritionLogService = nutritionLogService;
        this.userService = userService;
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
    public ResponseEntity<NutritionLog> addNutritionalLog(@RequestBody NutritionLogDto nutritionLogDto) {
        User user = userService.getUserById(nutritionLogDto.getUser_id());
        NutritionLog nutritionLog = new NutritionLog(user, nutritionLogDto.getNutrition_type(), nutritionLogDto.getDate(), nutritionLogDto.getFood(), nutritionLogDto.getCalories(), nutritionLogDto.getProtein(), nutritionLogDto.getCarbohydrate(), nutritionLogDto.getFat());
        return ResponseEntity.ok(nutritionLogService.createNutritionLog(nutritionLog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutritionLogById(@PathVariable Long id) {
        nutritionLogService.deleteNutritionLogById(id);
        return ResponseEntity.noContent().build();
    }
}
