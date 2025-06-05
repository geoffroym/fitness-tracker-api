package org.example.fitnesstrackerapi.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.fitnesstrackerapi.model.enums.NutritionType;

import java.util.Date;

public class NutritionLogDto {

    private Long user_id;
    @Enumerated(EnumType.STRING)
    private NutritionType nutrition_type;
    private Date date;
    private String food;
    private double calories;
    private double protein;
    private double carbohydrate;
    private double fat;

    public NutritionLogDto(Long user_id, NutritionType nutrition_type, Date date, String food, double calories, double protein, double carbohydrate, double fat) {
        this.user_id = user_id;
        this.nutrition_type = nutrition_type;
        this.date = date;
        this.food = food;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public NutritionType getNutrition_type() {
        return nutrition_type;
    }

    public void setNutrition_type(NutritionType nutrition_type) {
        this.nutrition_type = nutrition_type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }
}
