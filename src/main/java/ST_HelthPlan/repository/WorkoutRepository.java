package ST_HelthPlan.repository;

import ST_HelthPlan.domain.User;
import ST_HelthPlan.domain.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUser(User user);
}