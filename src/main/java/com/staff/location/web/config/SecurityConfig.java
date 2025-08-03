package com.staff.location.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .headers(headers -> {
                // X-Content-Type-Options is enabled by default in Spring Security 6.1+
                headers.frameOptions(frame -> frame.deny()); // Adds X-Frame-Options: DENY
            });
        return http.build();
    }
}
