package com.example.usermanagement.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
            http
                    .csrf(csrf -> csrf.disable()) // Disables CSRF for REST APIs
                    .authorizeHttpRequests(auth -> auth
                            .anyRequest().permitAll() // Disables token authorization checks for local testing
                    );
            return http.build();
        }
    }


