package org.example.fitnesstrackerapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.example.fitnesstrackerapi.model.enums.ExerciseType;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "exercise_gen")
    @SequenceGenerator(name = "exercise_gen", sequenceName = "exercise_seq", allocationSize = 1)
    @Column(name= "exercise_id")
    private long exerciseId;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    @JsonIgnoreProperties("exercise")
    private Workout workout_id;

    @Column(name = "exercise_name")
    private String exerciseName;
    private int sets;
    private int reps;

    @Column(name = "weight_kg")
    private double weightKg;

    @Enumerated(EnumType.STRING)
    private ExerciseType type;

    public Exercise() {
    }

    public Exercise(Workout workout_id, String exerciseName, int sets, int reps, double weightKg, ExerciseType type) {
        this.workout_id = workout_id;
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.weightKg = weightKg;
        this.type = type;
    }

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Workout getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(Workout workout_id) {
        this.workout_id = workout_id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
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

    @Override
    public String toString() {
        return "Exercise{" +
                "exerciseId=" + exerciseId +
                ", workout_id=" + workout_id +
                ", exerciseName='" + exerciseName + '\'' +
                ", sets=" + sets +
                ", reps=" + reps +
                ", weightKg=" + weightKg +
                ", type=" + type +
                '}';
    }
}
