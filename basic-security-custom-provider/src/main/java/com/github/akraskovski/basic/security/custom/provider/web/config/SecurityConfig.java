package com.github.akraskovski.basic.security.custom.provider.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * General security configuration class.
 *
 * @EnableGlobalMethodSecurity - prePostEnabled = true gives possibility to use @PreAuthorize("pattern") on methods
 * - securedEnabled = true gives possibility to use @Secured("role"), where role is granted authority from the
 * {@link org.springframework.security.core.userdetails.UserDetails}.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UnauthorizedEntryPoint authenticationEntryPoint;
    private final AuthenticationProvider customAuthenticationProvider;

    public SecurityConfig(final UnauthorizedEntryPoint authenticationEntryPoint, final AuthenticationProvider customAuthenticationProvider) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
//        Parent implementation will build all authorized requests with default username-password formLogin.
//        super.configure(http);

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/foo/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/foo/public").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);
    }
}
