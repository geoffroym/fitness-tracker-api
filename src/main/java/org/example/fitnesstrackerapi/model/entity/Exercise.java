package org.example.fitnesstrackerapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.fitnesstrackerapi.model.enums.ExerciseType;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "exercise_gen")
    @SequenceGenerator(name = "exercise_gen", sequenceName = "exercise_seq", allocationSize = 1)
    private long exerciseId;

    @ManyToOne
    private Workout workout_id;

    private String exerciseName;
    private int sets;
    private int reps;
    private double weightKg;

    @Enumerated(EnumType.STRING)
    private ExerciseType type;

    public Exercise(Workout workout_id, String exerciseName, int sets, int reps, double weightKg, ExerciseType type) {
        this.workout_id = workout_id;
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.weightKg = weightKg;
        this.type = type;
    }
}
