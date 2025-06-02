package org.example.fitnesstrackerapi.controller;

import org.example.fitnesstrackerapi.dto.WorkoutDto;
import org.example.fitnesstrackerapi.model.entity.User;
import org.example.fitnesstrackerapi.model.entity.Workout;
import org.example.fitnesstrackerapi.service.UserService;
import org.example.fitnesstrackerapi.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final UserService userService;

    @Autowired
    public WorkoutController(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Workout>> getWorkouts() {
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getWorkoutById(@PathVariable long id) {
        Workout workout = workoutService.getWorkoutById(id);
        return ResponseEntity.ok(workout.toString());
    }

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@RequestBody WorkoutDto workoutDto) {
        User user = userService.getUserById(workoutDto.getUser_id());
        Workout workout1 = new Workout(user, workoutDto.getDate(), workoutDto.getDurationMinutes());
        return ResponseEntity.ok(workoutService.createWorkout(workout1));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkout(@PathVariable long id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }
}
