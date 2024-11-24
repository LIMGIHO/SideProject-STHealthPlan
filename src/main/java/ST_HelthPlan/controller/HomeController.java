package ST_HelthPlan.controller;

import ST_HelthPlan.domain.Program;
import ST_HelthPlan.domain.User;
import ST_HelthPlan.repository.ProgramRepository;
import ST_HelthPlan.repository.UserRepository;
import ST_HelthPlan.service.ProgramService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    ProgramService programService;
    @Autowired
    UserRepository userRepository;

    @GetMapping({ "/", "" })
    public String root() {
        return "redirect:/home";
    }

    @GetMapping({ "/home" })
    public String home(@AuthenticationPrincipal OidcUser principal, Model model) {
        if (principal == null) {
        }

        String email = principal.getAttribute("email");
        String name = principal.getAttribute("name");

        // 사용자 정보 저장 또는 업데이트
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setName(name); // 사용자 정보 업데이트
        } else {
            user = new User();
            user.setEmail(email);
            user.setName(name);
        }
        user = userRepository.save(user);
        List<Program> program = programService.findProgramByUser(user.getId());

        System.out.println("ID:" + user.getId() + " Program List : " + program.toString());

        model.addAttribute("user", user);
        model.addAttribute("program", program);
        return "home";
    }
    // @GetMapping("/home")
    // public String home(HttpSession session, Model model) {
    // User user = (User) session.getAttribute("user");
    // if (user != null) {
    // model.addAttribute("user", user);
    // }
    // return "home"; // templates/home.html을 찾도록 함
    // }

    @PostMapping("/saveDefaultProgram")
    @ResponseBody
    public void saveDefaultProgram(@RequestBody Program entity) {
        System.out.println("saveDefaultProgram : " + entity.getProgramId() + " / " + entity.getDef());
        programService.saveDefaultProgram(entity.getProgramId(), entity.getDef());
    }

}
