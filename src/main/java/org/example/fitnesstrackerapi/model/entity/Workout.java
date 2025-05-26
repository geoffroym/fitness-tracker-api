package org.example.fitnesstrackerapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "workout_gen")
    @SequenceGenerator(name = "workout_gen", sequenceName = "workout_seq", allocationSize = 1)
    private Long workoutId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("workout")
    private User user_id;

    private Date date;
    private int durationMinutes;

    public Workout(User user_id, Date date, int durationMinutes) {
        this.user_id = user_id;
        this.date = date;
        this.durationMinutes = durationMinutes;
    }
}
