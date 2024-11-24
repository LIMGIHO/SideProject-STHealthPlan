package ST_HelthPlan.repository;

import ST_HelthPlan.domain.ProgramDetail;
import ST_HelthPlan.domain.ProgramDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramDetailRepository extends JpaRepository<ProgramDetail, ProgramDetailID> {

    @Query("SELECT COALESCE(MAX(w.id.seq), 0) FROM ProgramDetail w WHERE w.id.programId = :programId")
    Long findMaxSeqByProgramId(@Param("programId") Long programId);

    @Query("SELECT w FROM ProgramDetail w WHERE w.id.programId = :programId")
    List<ProgramDetail> findByProgramId(@Param("programId") Long programId);
}