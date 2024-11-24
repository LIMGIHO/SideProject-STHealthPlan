package com.example.ST_HelthPlan;

import ST_HelthPlan.StHelthPlanApplication;
import ST_HelthPlan.domain.Program;
import ST_HelthPlan.domain.ProgramDetail;
import ST_HelthPlan.domain.ProgramDetailID;
import ST_HelthPlan.domain.User;
import ST_HelthPlan.repository.ProgramDetailRepository;
import ST_HelthPlan.repository.UserRepository;
import ST_HelthPlan.service.ProgramDetailService;
import ST_HelthPlan.service.ProgramService;
import ST_HelthPlan.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = StHelthPlanApplication.class)
class StHelthPlanApplicationTests {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProgramService programService;
	@Autowired
	ProgramDetailService programDetailService;
	@Autowired
	private ProgramDetailRepository programDetailRepository;

	@Test
	void 회원등록() {
		User user = new User();
		user.setName("test");
		user.setEmail("test@test.com");

		userService.registerUser(user);
		Optional<User> foundUser = userService.findUserByEmail("test@test.com");
		foundUser.ifPresentOrElse(
				u -> System.out.println(u.getName()),
				() -> System.out.println("no User"));

	}

	@Test
	void 프로그램등록() {
		Program program = new Program();
		program.setProgramName("Test Program5");
		program.setWorkCycle(1);
		program.setRestCycle(1);

		User user = new User();
		user.setId(1L);
		user.setName("Stephen");
		user.setEmail("lasid84@naver.com");
		program.setUserId(user.getId());

		programService.save(program);

		// 저장된 Program 엔티티를 검증하는 로직 추가
		List<Program> programList = programService.findProgramByUser(user.getId());
		System.out.println(programList.toString());
		assert programList != null;
	}

	@Test
	void 프로그램디테일등록() {
		ProgramDetail programDetail = new ProgramDetail();
		ProgramDetailID id = new ProgramDetailID();
		id.setProgramId(6L);
		id.setSeq(programDetailService.generateNextSeqByProgramId(id.getProgramId()));
		programDetail.setId(id);
		programDetail.setDetailName("Squat");
		programDetail.setReps(5);
		programDetail.setSets(5);
		programDetail.setWeight(180);

		programDetailRepository.save(programDetail);

		List<ProgramDetail> detail = programDetailService.findByProgramId(programDetail.getId().getProgramId());
		System.out.println(detail.toString());
	}

}
