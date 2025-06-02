package org.example.fitnesstrackerapi;

import com.github.javafaker.Faker;
import org.example.fitnesstrackerapi.model.entity.Exercise;
import org.example.fitnesstrackerapi.model.entity.User;
import org.example.fitnesstrackerapi.model.entity.Workout;
import org.example.fitnesstrackerapi.model.enums.ExerciseType;
import org.example.fitnesstrackerapi.model.enums.Goal;
import org.example.fitnesstrackerapi.repository.UserRepo;
import org.example.fitnesstrackerapi.service.ExerciseService;
import org.example.fitnesstrackerapi.service.UserService;
import org.example.fitnesstrackerapi.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class InitialData implements CommandLineRunner {

    private final UserService userService;
    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;
    private final Faker faker = new Faker();

    @Autowired
    public InitialData(UserService userService, WorkoutService workoutService, ExerciseService exerciseService) {
        this.userService = userService;
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<User> users = new ArrayList<>();
        List<Workout> workoutList = new ArrayList<>();
        List<Exercise> exerciseList = new ArrayList<>();

        Goal[] goals = Goal.values();
        ExerciseType[] exerciseTypes = ExerciseType.values();

        String[] exercises = {
                "Deadlift", "Squat", "Bench Press", "Pull-ups", "Push-ups", "Lunges",
                "Plank", "Overhead Press", "Barbell Row", "Bicep Curl", "Tricep Dip",
                "Running", "Cycling", "Swimming", "Rowing", "Jump Rope", "Yoga", "HIIT",
                "Muay Thai", "Boxing", "Kick-backs", "BJJ"
        };

        for(int i = 0; i < 10; i++){
            Goal randomGoal = goals[(int) (Math.random() * goals.length)];
            users.add(
                    userService.createUser(
                            new User(
                                  faker.name().fullName(),
                                    faker.internet().emailAddress(),
                                    faker.dragonBall().character(),
                                    faker.date().birthday(18, 100),
                                    faker.number().randomDouble(2, 150, 200),
                                    faker.number().randomDouble(2, 45, 200),
                                    randomGoal
                            )
                    )
            );
        }

        for(int i = 0; i < 10; i++){
            User randomUser = users.get((int) (Math.random() * users.toArray().length));
            workoutList.add(
                    workoutService.createWorkout(
                            new Workout(
                                    randomUser,
                                    faker.date().between(
                                            new SimpleDateFormat("yyyy-MM-dd").parse("2025-01-01"),
                                            new SimpleDateFormat("yyyy-MM-dd").parse("2025-05-31")
                                    ),
                                    faker.number().numberBetween(20,120)
                            )
                    )
            );
        }

        for(int i = 0; i < 15; i++){
            Workout randomWorkout = workoutList.get((int) (Math.random() * workoutList.toArray().length));
            ExerciseType randomType = exerciseTypes[(int) (Math.random() * exerciseTypes.length)];
            String randomExerciseName = exercises[new Random().nextInt(exercises.length)];

            exerciseList.add(
                    exerciseService.addExercise(
                            new Exercise(
                                    randomWorkout,
                                    randomExerciseName,
                                    faker.number().numberBetween(1, 6),
                                    faker.number().numberBetween(1, 25),
                                    faker.number().randomDouble(3, 1, 200),
                                    randomType
                            )
                    )
            );
        }


    }
}
