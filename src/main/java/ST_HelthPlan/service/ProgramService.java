package ST_HelthPlan.service;

import ST_HelthPlan.domain.Program;
import ST_HelthPlan.domain.ProgramDetail;
import ST_HelthPlan.domain.User;
import ST_HelthPlan.repository.ProgramRepository;
import ST_HelthPlan.repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    @Autowired
    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public Program save(Program program) {
        return programRepository.save(program);
    }

    @Query("SELECT w FROM Program w WHERE w.user = :userId")
    public List<Program> findProgramByUser(Long userId) {
        return programRepository.findByUserId(userId);
    }

    @Transactional
    public void saveDefaultProgram(Long programId, boolean def) {
        programRepository.updateDefByProgramId(def, programId);
    }

}
