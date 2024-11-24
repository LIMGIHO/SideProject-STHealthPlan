package ST_HelthPlan.domain;

import jakarta.persistence.*;

@Entity
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programId;
    private String programName;
    private int workCycle;
    private int restCycle;
    @Column(nullable = true)
    private int restTime;
    @Column(nullable = true)
    private boolean def;
    private long download;
    @Column(name = "user_id")
    private Long userId; // User의 ID만 저장하도록 변경

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    // getters and setters

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getWorkCycle() {
        return workCycle;
    }

    public void setWorkCycle(int workCycle) {
        this.workCycle = workCycle;
    }

    public int getRestCycle() {
        return restCycle;
    }

    public void setRestCycle(int restCycle) {
        this.restCycle = restCycle;
    }

    public boolean getDef() {
        return def;
    }

    public void setDef(boolean def) {
        this.def = def;
    }

    public long getDownload() {
        return download;
    }

    public void setDownload(long download) {
        this.download = download;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId=" + programId +
                ", programName='" + programName + '\'' +
                ", workCycle=" + workCycle +
                ", restCycle=" + restCycle +
                ", def='" + def + '\'' +
                ", download=" + download +
                ", userId=" + userId +
                ", user=" + user +
                '}';
    }
}
