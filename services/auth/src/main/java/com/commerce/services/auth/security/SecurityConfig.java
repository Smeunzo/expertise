package com.commerce.services.auth.security;

import com.commerce.services.auth.entity.AuthCredentials;
import com.commerce.services.auth.security.filter.JwtAuthenticationFilter;
import com.commerce.services.auth.security.filter.JwtAuthorizationFilter;
import com.commerce.services.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;

@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthService authService;

    public SecurityConfig(AuthService authService) {
        this.authService = authService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            AuthCredentials authCredentials = authService.loadUserByUsername(username);
            return new User(authCredentials.getUsername(), authCredentials.getPassword(), new ArrayList<>());
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().frameOptions().disable()
                .and().authorizeHttpRequests().antMatchers("/users/**", "/refreshToken").permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
