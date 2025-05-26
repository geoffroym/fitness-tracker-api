package org.example.fitnesstrackerapi;

import org.springframework.boot.SpringApplication;

public class TestFitnessTrackerApiApplication {

    public static void main(String[] args) {
        SpringApplication.from(FitnessTrackerApiApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
