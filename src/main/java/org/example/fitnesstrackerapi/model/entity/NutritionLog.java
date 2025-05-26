package org.example.fitnesstrackerapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.fitnesstrackerapi.model.enums.NutritionType;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class NutritionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "nutrition_gen")
    @SequenceGenerator(name = "nutrition_gen", sequenceName = "nutrition_seq", allocationSize = 1)
    private Long nutrition_id;

    @ManyToOne
    private User user_id;

    @Enumerated(EnumType.STRING)
    private NutritionType nutrition_type;

    private Date date;
    private String food;
    private float calories;
    private float protein;
    private float carbohydrate;
    private float fat;

    public NutritionLog(User user_id, NutritionType nutrition_type, Date date, String food, float calories, float protein, float carbohydrate, float fat) {
        this.user_id = user_id;
        this.nutrition_type = nutrition_type;
        this.date = date;
        this.food = food;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
    }
}
