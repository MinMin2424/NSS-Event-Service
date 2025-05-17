/*
 * Created by minmin_tranova on 17.05.2025
 */

package cz.cvut.fel.NSS_event_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // vypnutí CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // všechny endpointy vyžadují auth
                )
                .httpBasic(Customizer.withDefaults()); // povolení Basic Auth

        return http.build();
    }
}
