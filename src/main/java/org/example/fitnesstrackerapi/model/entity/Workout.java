package org.example.fitnesstrackerapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "workout_gen")
    @SequenceGenerator(name = "workout_gen", sequenceName = "workout_seq", allocationSize = 1)
    @Column(name = "workout_id")
    private Long workoutId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("workout")
    private User user_id;

    @Column(name = "workout_date")
    private Date date;
    @Column(name = "duration_minutes")
    private int durationMinutes;

    public Workout() {
    }

    public Workout(User user_id, Date date, int durationMinutes) {
        this.user_id = user_id;
        this.date = date;
        this.durationMinutes = durationMinutes;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "workoutId=" + workoutId +
                ", user_id=" + user_id +
                ", date=" + date +
                ", durationMinutes=" + durationMinutes +
                '}';
    }
}
