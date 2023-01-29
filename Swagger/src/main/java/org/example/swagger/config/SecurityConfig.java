package org.example.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .antMatchers("/login", "/registration").permitAll()
                        .antMatchers("/api/v1/users/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic()
                .and()
                .formLogin()
                .defaultSuccessUrl("/users", true)
                .permitAll()
                .and()
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
