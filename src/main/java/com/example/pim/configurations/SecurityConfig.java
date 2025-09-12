package com.example.pim.configurations;

import com.example.pim.services.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Permissões de Login
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()

                        // Permissões de tecnicos
                        .requestMatchers(HttpMethod.POST, "/tec/register/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/tec/update/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/tec/delete/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tec/consultation/**").permitAll()

                        // Permissões de clientes
                        .requestMatchers(HttpMethod.POST, "/client/register/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/client/update/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/client/delete/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/client/consultation/**").permitAll()

                        // Permissões de chamado
                        .requestMatchers(HttpMethod.POST, "/called/register/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/called/update/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/called/delete/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/called/consultation/**").permitAll()

                        // Qualquer outra requisição precisa estar autenticada
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        return authentication -> {
            String username = authentication.getName();
            String password = authentication.getCredentials().toString();

            var userDetails = userDetailsService.loadUserByUsername(username);

            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new org.springframework.security.authentication.BadCredentialsException("Senha inválida");
            }

            return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    userDetails.getAuthorities()
            );
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
