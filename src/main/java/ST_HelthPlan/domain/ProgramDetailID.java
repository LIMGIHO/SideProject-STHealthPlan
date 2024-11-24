package ST_HelthPlan.domain;


import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProgramDetailID implements Serializable {

    private Long programId;
    private Long seq;

    public ProgramDetailID() {
    }

    public ProgramDetailID(Long programId, Long seq) {
        this.programId = programId;
        this.seq = seq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramDetailID that = (ProgramDetailID) o;
        return Objects.equals(seq, that.seq) && Objects.equals(programId, that.programId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq, programId);
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}
