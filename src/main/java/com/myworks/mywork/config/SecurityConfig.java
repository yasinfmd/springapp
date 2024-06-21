package com.myworks.mywork.config;

import com.myworks.mywork.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    private AccessDeniedHandler customAccessDeniedHandler;
    private static final String[] WHITE_LIST_URL = {"/api/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};
    private final JwtAuthFilter jwtAuthFilter;
    private final   AuthenticationProvider authenticationProvider;

    @Bean
    AccessDeniedHandler customAccessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{\"code\": \"403\", \"message\": \"Erişim Reddedildi\", \"timestamp\": \"" + java.time.LocalDateTime.now() + "\", \"path\": \"" + request.getRequestURI() + "\", \"success\": false}");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        String[] publicUrls = {};
        String[] protectedUrls = {"/api/todos/**"};
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authentication ->
                        //hasAnyRole(ADMIN.name(),Manager.name())
                        //hasAnuauthority (ADMIN_READ.name())
                        authentication
                                .requestMatchers(protectedUrls).authenticated()
                                .requestMatchers(WHITE_LIST_URL).permitAll()
                                .requestMatchers("/gs-guide-websocket").permitAll()
                                .anyRequest().permitAll()

                )
                .sessionManagement((manager) -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(customizer->customizer.accessDeniedHandler(customAccessDeniedHandler()))
                .logout(customizer->customizer.logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext())));


        return http.build();
    }






}