package org.example.fitnesstrackerapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.fitnesstrackerapi.model.enums.Goal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    private Long userId;
    private String name;
    private String email;
    private String password; //hashed
    private int age;
    private double height;
    private double weight;
    @Enumerated(EnumType.STRING)
    private Goal goal;

    public User(String name, String email, String password, int age, double height, double weight, Goal goal) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.goal = goal;
    }
}
