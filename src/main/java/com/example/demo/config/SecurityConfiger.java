//package com.example.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@Component
//@EnableWebMvc
//public class SecurityConfiger {
//
//    @Autowired
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    @Autowired
//    private JwtAuthentifcationFilter jwtAuthentifcationFilter;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        // Configure CSRF and CORS settings
//        http.csrf(csrf -> csrf.disable())
//            .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
//                @Override
//                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                    CorsConfiguration cfg = new CorsConfiguration();
//                    cfg.setAllowedOrigins(Arrays.asList(
//                            "http://localhost:3000", "http://localhost:4200", "https://localhost/", 
//                            "http://hrms.localhost", "https://sk-bit.site","https://sk-bit.in"));
//                    cfg.setAllowedMethods(Collections.singletonList("*"));
//                    cfg.setAllowCredentials(true);
//                    cfg.setAllowedHeaders(Collections.singletonList("*"));
//                    cfg.setExposedHeaders(Arrays.asList("Authorization"));
//                    cfg.setMaxAge(3600L);
//                    return cfg;
//                }
//            }));
//
//        // Authorization rules and access control
//        http.authorizeRequests(auth -> auth
//                .requestMatchers("/auth/login/**", "/authenticate", "/v3/api-docs/**", "/swagger-resources/**", 
//                                 "/swagger-ui.html", "/swagger-ui/**", "/webjars/**")
//                .permitAll()
//                .requestMatchers("/api/jobapplication/save", "/api/jobapplication/upload-file")  // Unrestricted access to file upload and save
//                .permitAll()
//                .requestMatchers("/admin/**").hasRole("ADMIN")  // Restrict access to admin endpoints
//                .requestMatchers("/api/leaves/update2**").hasRole("EMPLOYEE")
//                .requestMatchers("/api/leaves/admin/**").hasRole("ADMIN")
//                .requestMatchers("/api/jobapplication/update/**").hasRole("ADMIN")
//                .requestMatchers("/api/jobapplication/delete/**").hasRole("ADMIN")
//                .requestMatchers("/employee/**").permitAll()  // Allow unrestricted access to employee endpoints
//                .requestMatchers("/attendance/**").permitAll()
//                
//                .requestMatchers("/password/email/**").permitAll()
//                .requestMatchers("/job/activejob").permitAll()
//                .requestMatchers("/api/jobapplication/save").permitAll()
//                .requestMatchers("/password/check").permitAll()
//                .anyRequest().authenticated())  // Protect all other endpoints
//            .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        // Adding the JWT authentication filter
//        http.addFilterBefore(jwtAuthentifcationFilter, UsernamePasswordAuthenticationFilter.class);
//
//
//        return http.build();
//    }
//
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();  // Use BCrypt for password hashing
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) 
//            throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//}
