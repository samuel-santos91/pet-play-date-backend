package com.sam.backend.config;

import com.sam.backend.jwt.JwtAuthFilter;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Autowired
  private JwtAuthFilter jwtAuthFilter;

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .csrf(CsrfConfigurer::disable)
      .authorizeHttpRequests(requests ->
        requests
          .dispatcherTypeMatchers(DispatcherType.ERROR)
          .permitAll()
          .requestMatchers("/auth/register")
          .permitAll()
          .requestMatchers("/auth/login")
          .permitAll()
          // .requestMatchers("/users") //REMOVE THIS
          // .permitAll()
          .anyRequest()
          .authenticated()
      )
      .addFilterBefore(
        jwtAuthFilter,
        UsernamePasswordAuthenticationFilter.class
      )
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      );
    return http.build();
  }
}
