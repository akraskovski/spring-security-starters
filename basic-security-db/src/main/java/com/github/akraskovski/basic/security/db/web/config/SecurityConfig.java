package com.github.akraskovski.basic.security.db.web.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

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
    //TODO: create custom user details service
    private final UserDetailsService userDetailsService;
    private final DataSource dataSource;

    public SecurityConfig(final UnauthorizedEntryPoint authenticationEntryPoint, final UserDetailsService userDetailsService, @Qualifier("securityDataSource") final DataSource dataSource) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userDetailsService = userDetailsService;
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .and()
                .userDetailsService(userDetailsService);
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
