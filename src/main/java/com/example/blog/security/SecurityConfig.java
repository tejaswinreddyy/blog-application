package com.example.blog.security;

import com.example.blog.util.ResponseBuilderFactory;
import com.example.blog.util.ResponseStructure;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    private final ResponseBuilderFactory responseBuilderFactory;


    public String clientOrigin;

    public SecurityConfig(
            ResponseBuilderFactory responseBuilderFactory,
            @Value("${app.client.origin}") String clientOrigin
    ) {
        this.responseBuilderFactory = responseBuilderFactory;
        this.clientOrigin = clientOrigin;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.GET, "/blogs/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "/register/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .successHandler(this::successHandler)
                        .failureHandler(this::failureHandler))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(this::logoutSuccessHandler)
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(this::unauthorizedHandler))
                .build();
    }

    private void failureHandler(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        writeJsonResponse(response, responseBuilderFactory.respondWith(HttpStatus.UNAUTHORIZED, exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    private void successHandler(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        writeJsonResponse(response, responseBuilderFactory.ok("Login Successful", authentication.getPrincipal()), HttpStatus.OK);
    }


    private <T> void writeJsonResponse(HttpServletResponse response, T body, HttpStatus status) throws IOException {
        try {
            response.setStatus(status.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(), body);
        } catch (IOException e) {
            log.error("Error writing JSON response", e);
        }
    }

    @Bean
    @Primary
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(clientOrigin);
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private void unauthorizedHandler(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        writeJsonResponse(response, responseBuilderFactory.respondWith(HttpStatus.UNAUTHORIZED, "Unauthorized: Please log in first"),HttpStatus.UNAUTHORIZED);

    }
    private void logoutSuccessHandler(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Logout successful\"}");
    }



}
