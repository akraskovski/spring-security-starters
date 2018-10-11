package com.github.akraskovski.resource.server.remote.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * Configuration this module as an OAuth2.0 Resource Server instance.
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${resource.id:company_api}")
    private String resourceId;

    @Value("${auth.server.connection.url}")
    private String checkTokenUrl;

    @Value("${auth.server.connection.clientId}")
    private String clientId;

    @Value("${auth.server.connection.clientSecret}")
    private String clientSecret;

    /**
     * {@link RemoteTokenServices} custom configuration for the remote connection to the Authorization Server.
     * Used for verifying incoming tokens in requests to the resource server.
     *
     * @return configured remote token service
     */
    @Bean
    @Primary
    public RemoteTokenServices remoteTokenServices() {
        final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(checkTokenUrl);
        remoteTokenServices.setClientId(clientId);
        remoteTokenServices.setClientSecret(clientSecret);
        return remoteTokenServices;
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) {
        resources.tokenServices(remoteTokenServices()).resourceId(resourceId).stateless(true);
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/company").access("#oauth2.hasScope('read')")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
