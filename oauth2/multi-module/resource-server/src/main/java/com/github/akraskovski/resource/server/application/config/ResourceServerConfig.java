package com.github.akraskovski.resource.server.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * Configuration this module as an OAuth2.0 Resource Server instance.
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "company_api";

    @Bean
    @ConfigurationProperties(prefix = "auth.datasource")
    public DataSource authServerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) {
        final TokenStore tokenStore = new JdbcTokenStore(authServerDataSource());
        resources.resourceId(RESOURCE_ID).tokenStore(tokenStore);
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/company").access("#oauth2.hasScope('read')")
                .anyRequest().permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
