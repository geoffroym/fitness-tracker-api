package org.example.fitnesstrackerapi;

import com.github.javafaker.Faker;
import org.example.fitnesstrackerapi.model.entity.User;
import org.example.fitnesstrackerapi.model.enums.Goal;
import org.example.fitnesstrackerapi.repository.UserRepo;
import org.example.fitnesstrackerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class InitialData implements CommandLineRunner {

    private final UserService userService;
    private final UserRepo userRepo;
    private final Faker faker = new Faker();

    @Autowired
    public InitialData(UserService userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepo.count() == 0) {
            User testUser = new User("Cami", "cami@mail.com", "password123", faker.date().birthday(18, 100), 170, 65, Goal.MAINTENANCE);
            userRepo.save(testUser);
        }

        List<User> users = new ArrayList<>();

        Goal[] goals = Goal.values();
        Goal randomGoal = goals[(int) (Math.random() * goals.length)];



        for(int i = 0; i < 20; i++){
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

    }
}
