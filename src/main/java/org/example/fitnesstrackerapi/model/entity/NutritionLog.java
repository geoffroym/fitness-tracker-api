package org.example.fitnesstrackerapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.fitnesstrackerapi.model.enums.NutritionType;

import java.util.Date;

@Entity
@Table(name = "nutrition_log")
public class NutritionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "nutrition_gen")
    @SequenceGenerator(name = "nutrition_gen", sequenceName = "nutrition_seq", allocationSize = 1)
    private Long nutrition_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("nutrition_log")
    private User user_id;

    @Column(name = "nutrition_type")
    @Enumerated(EnumType.STRING)
    private NutritionType nutrition_type;

    @Column(name = "log_date")
    private Date date;
    private String food;
    private double calories;
    private double protein;
    private double carbohydrate;
    private double fat;

    public NutritionLog() {
    }

    public NutritionLog(User user_id, NutritionType nutrition_type, Date date, String food, double calories, double protein, double carbohydrate, double fat) {
        this.user_id = user_id;
        this.nutrition_type = nutrition_type;
        this.date = date;
        this.food = food;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
    }

    public Long getNutrition_id() {
        return nutrition_id;
    }

    public void setNutrition_id(Long nutrition_id) {
        this.nutrition_id = nutrition_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
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
