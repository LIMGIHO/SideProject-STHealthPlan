package ST_HelthPlan.repository;

import ST_HelthPlan.domain.Program;
import ST_HelthPlan.domain.ProgramDetail;
import ST_HelthPlan.domain.ProgramDetailID;
import ST_HelthPlan.domain.User;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    List<Program> findByUserId(Long userId);

    // @Modifying
    // @Transactional
    // void updateDefByProgramId(boolean def, Long programId);

    @Modifying
    @Query("UPDATE Program p SET p.def = :def WHERE p.id = :programId")
    void updateDefByProgramId(@Param("def") boolean def, @Param("programId") Long programId);

}