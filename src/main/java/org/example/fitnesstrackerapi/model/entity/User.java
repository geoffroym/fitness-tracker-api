package org.example.fitnesstrackerapi.model.entity;

import jakarta.persistence.*;
import org.example.fitnesstrackerapi.model.enums.Goal;

import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_gen")
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

    public User(){
    }

    public User(String name, String email, String password, Date dayOfBirth, double height, double weight, Goal goal) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dayOfBirth = dayOfBirth;
        this.height = height;
        this.weight = weight;
        this.goal = goal;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dayOfBirth=" + dayOfBirth +
                ", height=" + height +
                ", weight=" + weight +
                ", goal=" + goal +
                '}';
    }
}
