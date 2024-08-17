package com.ganga.food_app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ganga.food_app.services.CustomUserSecurityDetailService;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserSecurityDetailService userSecurityDetailService;
    private final AuthFailureHandler failureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userSecurityDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> {
            configurer.requestMatchers("/items").permitAll();
            configurer.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN");
            configurer.requestMatchers("/orders/**").hasAnyRole("USER", "ADMIN");
            configurer.requestMatchers("/address/**").hasAnyRole("USER", "ADMIN");
            configurer.requestMatchers("/admin/**").hasRole("ADMIN");
            configurer.requestMatchers("/profile/**").authenticated();
            configurer.requestMatchers("/delivery/assignAgent").hasRole("ADMIN");
            configurer.requestMatchers("/delivery/**").hasRole("DELIVERY");
            configurer.anyRequest().permitAll();
        });

        http.formLogin(customizer -> {
            customizer.loginPage("/auth/login");
            customizer.loginProcessingUrl("/authenticate");
            // customizer.successForwardUrl("/user/dashboard");
            // customizer.failureForwardUrl("/auth/login?error=true");
            customizer.usernameParameter("email");
            customizer.passwordParameter("password");
            customizer.failureHandler(failureHandler);
        });

        // http.authenticationProvider(authenticationProvider());
        http.csrf(AbstractHttpConfigurer::disable);


        http.logout(logouter -> {
            logouter.logoutUrl("/logout");
            logouter.logoutSuccessUrl("/auth/login?logout=true");
            logouter.invalidateHttpSession(true);
            logouter.deleteCookies("JSESSIONID");
        });



        return http.build();
    }
}
