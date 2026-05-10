package com.kashish.fortify.config;

import com.kashish.fortify.security.JwtAuthenticationFilter;
import com.kashish.fortify.security.JwtTokenProvider;
import com.kashish.fortify.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        JwtAuthenticationFilter filter =
                new JwtAuthenticationFilter(
                        jwtTokenProvider,
                        userDetailsService
                );

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // public endpoints
                .requestMatchers("/api/auth/**").permitAll()

                // role based endpoints
                .requestMatchers("/api/admin/**")
                .hasRole("ADMIN")

                .requestMatchers("/api/customer/**")
                .hasRole("CUSTOMER")

                .requestMatchers("/api/vendor/**")
                .hasRole("VENDOR")

                // test endpoint
                .requestMatchers("/api/test")
                .authenticated()

                .anyRequest().authenticated()
            )

            .addFilterBefore(
                    filter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}