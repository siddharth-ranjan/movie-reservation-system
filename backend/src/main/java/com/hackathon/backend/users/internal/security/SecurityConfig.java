package com.hackathon.backend.users.internal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
//                          .requestMatchers("/register").permitAll()
//                        // Permit access to public endpoints without authentication
//                        .requestMatchers("/api/public/**", "/login").permitAll()
//                        // Require ADMIN role for admin endpoints
//                        .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
//                        .requestMatchers("/api/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        // All other requests must be authenticated
                        .anyRequest().permitAll()
                );
                // Use form login for authentication
//                .formLogin(Customizer.withDefaults());

        return http.build();

    }
}
