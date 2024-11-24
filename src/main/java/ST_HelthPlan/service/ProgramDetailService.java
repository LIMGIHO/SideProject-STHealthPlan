package ST_HelthPlan.service;

import ST_HelthPlan.domain.ProgramDetail;
import ST_HelthPlan.repository.ProgramDetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProgramDetailService {
    @Autowired
    private ProgramDetailRepository programDetailRepository;

    public List<ProgramDetail> findByProgramId(Long programId) {
        return programDetailRepository.findByProgramId(programId);
    }

    public Long generateNextSeqByProgramId(Long programId) {
        Long maxSeq = programDetailRepository.findMaxSeqByProgramId(programId);
        return maxSeq + 1;
    }

//    public Workout saveWorkout(Workout workout) {
//        return workoutRepository.save(workout);
//    }
}
