package com.github.akraskovski.tts.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity // not necessary. WebSecurityEnablerConfiguration will do it by its own.
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
            .authorizeRequests()
            .antMatchers("/**").authenticated()
            .and()
            .csrf().disable()
            .oauth2Login().and().oauth2Client();
    }
}
