package com.example.runeshop_ecommerce.config;

import com.example.runeshop_ecommerce.entities.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/login","/auth/register", "/auth/refresh","/auth/registerAdmin", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                                .requestMatchers(HttpMethod.GET, "/producto/**", "/categoria/**", "/talle/**").permitAll()
                                .requestMatchers("/mercado/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(HttpMethod.GET,"/perfil/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/usuario/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/usuario/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST,"/perfil/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/direccion/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/producto/**").hasAuthority("ADMIN")
                                .anyRequest().hasAuthority("ADMIN")
                )
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint((request, response, authException) -> {
                                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                                    response.setContentType("application/json");
                                    response.getWriter().write(
                                            "{\"codigoError\": \"UNAUTHORIZED\", \"mensaje\": \"Autenticacion requerida\"}"
                                    );
                                })
                                .accessDeniedHandler(((request, response, accessDeniedException) -> {
                                    response.setStatus(HttpStatus.FORBIDDEN.value());
                                    response.setContentType("application/json");
                                    response.getWriter().write(
                                            "{\"codigoError\": \"FORBIDDEN\", \"mensaje\": \"No tienes permisos para este recurso\"}"
                                    );
                                }))
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}