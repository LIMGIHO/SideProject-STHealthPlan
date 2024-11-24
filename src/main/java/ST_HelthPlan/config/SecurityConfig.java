package ST_HelthPlan.config;

import ST_HelthPlan.domain.User;
import ST_HelthPlan.repository.ProgramRepository;
import ST_HelthPlan.repository.UserRepository;
import jakarta.servlet.FilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger LOGGER = Logger.getLogger(SecurityConfig.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // .csrf().disable()
                // .cors(cors -> cors
                // .configurationSource(request -> {
                // var corsConfig = new CorsConfiguration();
                // corsConfig.setAllowedOrigins(List.of("http://localhost:8080"));
                // corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE",
                // "OPTIONS"));
                // corsConfig.setAllowedHeaders(List.of("*"));
                // return corsConfig;
                // }))
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/", "/login").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .oidcUserService(oidcUserService()))
                        .successHandler(authenticationSuccessHandler()))
                .addFilterBefore(new CustomFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(logoutSuccessHandler())
                        // .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll())
        // .exceptionHandling(exceptionHandling ->
        // exceptionHandling
        // .accessDeniedPage("/error")
        // )
        ;
        return http.build();
    }

    @Bean
    public OidcUserService oidcUserService() {
        return new OidcUserService();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException, ServletException {
                OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
                String email = oAuth2User.getAttribute("email");
                String name = oAuth2User.getAttribute("name");

                System.out.println("Authenticated user: " + oAuth2User.getAttributes());

                // 세션에 사용자 정보 저장
                // request.getSession().setAttribute("user", user);
                response.sendRedirect("/home");
            }
        };
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException, ServletException {
                System.out.println("onLogoutSuccess1");
                if (authentication != null && authentication.getDetails() != null) {
                    try {
                        LOGGER.log(Level.INFO, "onLogoutSuccess2");
                        request.getSession().invalidate();
                        response.setStatus(HttpServletResponse.SC_OK);
                        response.sendRedirect("/login?logout");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        };
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    public class CustomFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                FilterChain filterChain) throws ServletException, IOException {
            try {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                LOGGER.log(Level.INFO, "CustomFilter out if / " + request.getRequestURI());
                if (request.getRequestURI().equals("/login") && authentication != null
                        && authentication.isAuthenticated()) {
                    LOGGER.log(Level.INFO, "CustomFilter in if");
                    response.sendRedirect("/home");
                    return;
                }
                LOGGER.log(Level.INFO, "CustomFilter 2");
                filterChain.doFilter(request, response);
                LOGGER.log(Level.INFO, "CustomFilter 3");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error during filtering request: " + request.getRequestURI(), e);
                // 에러 페이지로 리디렉션
                response.sendRedirect("/error");
            }
        }
    }

}
