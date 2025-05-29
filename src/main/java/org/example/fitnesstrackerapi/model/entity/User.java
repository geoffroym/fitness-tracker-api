package org.example.fitnesstrackerapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.fitnesstrackerapi.model.enums.Goal;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Long userId;
    private String name;
    private String email;
    private String password; //hashed
    @Column(name = "date_of_birth")
    private Date dayOfBirth;
    private double height;
    private double weight;
    @Enumerated(EnumType.STRING)
    private Goal goal;

    public User(String name, String email, String password, Date dayOfBirth, double height, double weight, Goal goal) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dayOfBirth = dayOfBirth;
        this.height = height;
        this.weight = weight;
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dayOfBirth=" + dayOfBirth +
                ", height=" + height +
                ", weight=" + weight +
                ", goal=" + goal +
                '}';
    }
}
