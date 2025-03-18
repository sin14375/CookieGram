package cookiegram.ca.application.config;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import cookiegram.ca.application.service.UserService;

/**
 * Security configuration for Cookiegram application.
 * 
 * This class configures authentication and authorization rules for different
 * user roles including Admin, Employee, and Customer.
 * 
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 * 
 * @author The Incredible Team
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserService userService;

    /**
     * Bean for password encoding using BCrypt.
     * 
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures authentication provider with UserDetailsService and password encoder.
     * 
     * @return DaoAuthenticationProvider instance
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    /**
     * Configures security settings, defining access rules, login/logout mechanisms,
     * and role-based redirections.
     * 
     * @param http The HttpSecurity instance
     * @return Configured SecurityFilterChain
     * @throws Exception in case of security configuration errors
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Public pages and static resources
                .requestMatchers("/index", "/", "/registration**", "/js/**", "/css/**", "/img/**")
                    .permitAll()
                // Role-based access control
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/employee/**").hasRole("EMPLOYEE")
                .requestMatchers("/customer/**").hasRole("USER")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .successHandler((request, response, authentication) -> {
                    // Redirect users based on their assigned roles
                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                    boolean isAdmin = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                    boolean isEmployee = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYEE"));
                    boolean isUser = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"));

                    if (isAdmin) {
                        response.sendRedirect("/admin/dashboard");
                    } else if (isEmployee) {
                        response.sendRedirect("/employee/dashboard");
                    } else if (isUser) {
                        response.sendRedirect("/customer/dashboard");
                    } else {
                        response.sendRedirect("/login?error=unknownRole");
                    }
                })
            )
            .logout(logout -> logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // Redirect to home page after logout
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.sendRedirect("/index");
                })
                .permitAll()
            );

        http.authenticationProvider(authenticationProvider());
        return http.build();
    }
}
