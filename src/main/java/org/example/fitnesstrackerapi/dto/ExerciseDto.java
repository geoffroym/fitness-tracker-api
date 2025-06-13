package org.example.fitnesstrackerapi.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.fitnesstrackerapi.model.enums.ExerciseType;

public class ExerciseDto {
    private Long workout_id;
    private String exercise_name;
    private int sets;
    private int reps;
    private double weightKg;
    @Enumerated(EnumType.STRING)
    private ExerciseType type;

    public Long getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(Long workout_id) {
        this.workout_id = workout_id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public ExerciseType getType() {
        return type;
    }

    public void setType(ExerciseType type) {
        this.type = type;
    }

    public ExerciseDto(Long workout_id, String exercise_name, int sets, int reps, double weightKg, ExerciseType type) {
        this.workout_id = workout_id;
        this.exercise_name = exercise_name;
        this.sets = sets;
        this.reps = reps;
        this.weightKg = weightKg;
        this.type = type;
    }
}
