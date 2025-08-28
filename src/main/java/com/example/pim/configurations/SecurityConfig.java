package com.example.pim.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/tec/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/tec/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/tec/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/tec/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/client/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/client/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/client/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/client/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
