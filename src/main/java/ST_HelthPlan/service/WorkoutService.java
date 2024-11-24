package ST_HelthPlan.service;

import ST_HelthPlan.domain.User;
import ST_HelthPlan.domain.Workout;
import ST_HelthPlan.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;

    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public List<Workout> getUserWorkouts(User user) {
        return workoutRepository.findByUser(user);
    }
}
