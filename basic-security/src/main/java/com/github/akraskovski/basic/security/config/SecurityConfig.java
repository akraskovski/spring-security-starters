package com.github.akraskovski.basic.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UnauthorizedEntryPoint authenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        Could use jdbc storage with a preconfigured datasource.
//        auth.jdbcAuthentication().dataSource(dataSourceBean()).passwordEncoder(passwordEncoderBean());

        // Building inside UserDetails model for the in memory auth.
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                .withUser("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER") // equals to the .authorities("ROLE_USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN");
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
