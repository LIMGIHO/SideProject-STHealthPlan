package ST_HelthPlan.domain;

import jakarta.persistence.*;

@Entity
public class ProgramDetail {

    @EmbeddedId
    private ProgramDetailID id;
    private String detailName;
    private int sets;
    private int reps;
    private int weight;
    @Column(nullable = true)
    private int restTime;

    public ProgramDetailID getId() {
        return id;
    }

    public void setId(ProgramDetailID id) {
        this.id = id;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ProgramDetail{" +
                "id=" + id +
                ", detailName='" + detailName + '\'' +
                ", sets=" + sets +
                ", reps=" + reps +
                ", weight=" + weight +
                '}';
    }
}
