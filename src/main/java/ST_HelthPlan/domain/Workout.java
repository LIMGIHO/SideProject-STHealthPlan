package ST_HelthPlan.domain;

import jakarta.persistence.*;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private int sets;
    private int reps;
    private int weight;
    // getters and setters
}
