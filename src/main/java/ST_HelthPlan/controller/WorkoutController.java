package ST_HelthPlan.controller;

import ST_HelthPlan.domain.User;
import ST_HelthPlan.domain.Workout;
import ST_HelthPlan.service.UserService;
import ST_HelthPlan.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;
    @Autowired
    private UserService userService;

    @PostMapping
    public Workout createWorkout(@RequestBody Workout workout) {
        return workoutService.createWorkout(workout);
    }

    @GetMapping
    public List<Workout> getUserWorkouts(@RequestParam Long userId) {
        User user = userService.findUserById(userId);
        return workoutService.getUserWorkouts(user);
    }
}
