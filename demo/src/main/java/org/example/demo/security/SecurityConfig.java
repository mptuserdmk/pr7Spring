package org.example.demo.security;

import org.example.demo.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Настраиваем провайдер аутентификации
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        http.authenticationProvider(provider);

        http
            .authorizeHttpRequests(auth -> auth
                // Публичный доступ
                .requestMatchers("/login", "/register", "/css/**", "/error", "/access-denied").permitAll()
                // Только ADMIN
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Модератор и Администратор — управление контентом
                .requestMatchers(
                    "/services/add", "/services/edit/**", "/services/delete/**",
                    "/categories/add", "/categories/edit/**", "/categories/delete/**",
                    "/portfolios/add", "/portfolios/edit/**", "/portfolios/delete/**",
                    "/appointments/book", "/appointments/edit/**", "/appointments/delete/**",
                    "/users/**"
                ).hasAnyRole("MODERATOR", "ADMIN")
                // Остальное — любой аутентифицированный
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/services", true)
                .failureUrl("/login?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .exceptionHandling(handling -> handling
                .accessDeniedPage("/access-denied")
            );

        return http.build();
    }
}
