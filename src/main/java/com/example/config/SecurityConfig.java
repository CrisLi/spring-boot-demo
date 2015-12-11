package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable() //
                    .antMatcher("/api/**") //
                    .authorizeRequests() //
                    .anyRequest() //
                    .hasAnyRole("USER", "ADMIN") //
                    .and() //
                    .httpBasic() //
                    .and() //
                    .sessionManagement() //
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

    }

    @Configuration
    @Order(2)
    public static class ActuatorWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable() //
                    .antMatcher("/actuators/**") //
                    .authorizeRequests() //
                    .anyRequest() //
                    .hasRole("ADMIN") //
                    .and() //
                    .httpBasic() //
                    .and() //
                    .sessionManagement() //
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

    }
}
