package ST_HelthPlan.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "workout_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Integer bodyWeight;
    private Integer basicSets;
    private Integer basicReps;
    private Integer basicIncrements;
    private Integer basicRest;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(Integer bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public Integer getBasicSets() {
        return basicSets;
    }

    public void setBasicSets(Integer basicSets) {
        this.basicSets = basicSets;
    }

    public Integer getBasicReps() {
        return basicReps;
    }

    public void setBasicReps(Integer basicReps) {
        this.basicReps = basicReps;
    }

    public Integer getBasicIncrements() {
        return basicIncrements;
    }

    public void setBasicIncrements(Integer basicIncrements) {
        this.basicIncrements = basicIncrements;
    }

    public Integer getBasicRest() {
        return basicRest;
    }

    public void setBasicRest(Integer basicRest) {
        this.basicRest = basicRest;
    }

}
