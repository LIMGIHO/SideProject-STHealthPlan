package ST_HelthPlan.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.logging.Level;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, @RequestParam(value = "logout", required = false) String logout) {
        if (logout != null) {
//            LOGGER.log(Level.INFO, "onLogoutSuccess2");
//            request.getSession().invalidate();
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.sendRedirect("/login?logout");
            System.out.println("logout1");
            model.addAttribute("message", "You have been logged out successfully");
        }
        return "login";
    }

    @GetMapping("/logout")
    @ResponseBody
    public void Logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("entered logout point");
        session.invalidate();
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            auth.setAuthenticated(false);
            SecurityContextHolder.clearContext();
            SecurityContextHolder.getContext().setAuthentication(null);
        }

        Cookie cookieWithSlash = new Cookie("JSESSIONID", null);
        cookieWithSlash.setPath(request.getContextPath() + "/");
        cookieWithSlash.setDomain("auth-server");
        cookieWithSlash.setMaxAge(0);
        response.addCookie(cookieWithSlash); // For Tomcat

        // Redirect to login page
        response.sendRedirect("/login");

        //아래는 gpt 생성
//        // 세션 무효화 및 쿠키 삭제
//        request.getSession().invalidate();
//        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
//        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
//        return "redirect:/login?logout";
    }


}

