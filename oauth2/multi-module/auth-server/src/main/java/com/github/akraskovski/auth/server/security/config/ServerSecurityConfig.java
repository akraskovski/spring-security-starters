package com.github.akraskovski.auth.server.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * General Authorization server application security configuration.
 */
@Configuration
@EnableWebSecurity
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService basicUserDetailsService;

    @Autowired
    public ServerSecurityConfig(final UserDetailsService basicUserDetailsService) {
        this.basicUserDetailsService = basicUserDetailsService;
    }

    /**
     * Making default {@link AuthenticationManager} become as a spring bean.
     *
     * @return auto-generated authentication manager
     * @throws Exception if something goes wrong...
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/signUp").permitAll()
                .anyRequest().authenticated()
                .and()
                .userDetailsService(basicUserDetailsService)
                .httpBasic();
    }
}
