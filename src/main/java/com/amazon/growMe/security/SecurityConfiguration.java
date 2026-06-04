package com.amazon.growMe.security;

import com.amazon.growMe.model.AppUser;
import com.amazon.growMe.repository.AppUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService(AppUserRepository appUserRepository) {
        return email -> {
            List<AppUser> users = appUserRepository.findAll();

            for (AppUser appUser : users) {
                if (appUser.getEmail().equals(email)) {
                    return new User(
                            appUser.getEmail(),
                            appUser.getPasswordHash(),
                            List.of(new SimpleGrantedAuthority("ROLE_USER"))
                    );
                }
            }

            throw new UsernameNotFoundException("User not found");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

        CustomAuthorizationFilterLoginUsernamePassword loginFilter =
                new CustomAuthorizationFilterLoginUsernamePassword(authenticationManager);

        loginFilter.setFilterProcessesUrl("/api/login");

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users").permitAll()
                        .requestMatchers("/api/login").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilter(loginFilter)
                .addFilterBefore(new CustomAuthorizationFilterToken(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}