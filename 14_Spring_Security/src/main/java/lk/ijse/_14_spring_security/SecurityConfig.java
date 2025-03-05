package lk.ijse._14_spring_security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(customizer->
                customizer.disable());
        http.authorizeHttpRequests(customizer->
                customizer.requestMatchers("/customer").permitAll()).sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).addFilterBefore("","");
        return http.build();
    }
}
