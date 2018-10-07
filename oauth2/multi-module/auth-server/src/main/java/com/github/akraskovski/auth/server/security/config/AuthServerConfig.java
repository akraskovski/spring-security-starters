package com.github.akraskovski.auth.server.security.config;

import com.github.akraskovski.auth.server.domain.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@Import(ServerSecurityConfig.class)
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String RESOURCE_ID = "company_api";
    private static final String CLIENT_ID = "curl-client";
    private static final String CLIENT_SECRET = "your-client-secret";
    private static final String[] SCOPES = {"do-anything", "read", "write"};

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService basicUserDetailsService;
    private final DataSource dataSource;

    @Autowired
    public AuthServerConfig(final AuthenticationManager authenticationManager, final UserDetailsService basicUserDetailsService, final DataSource dataSource) {
        this.authenticationManager = authenticationManager;
        this.basicUserDetailsService = basicUserDetailsService;
        this.dataSource = dataSource;
    }

    /**
     * Password encoder bean.
     *
     * @return created component
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer security) {
        security.passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(CLIENT_ID)
                .secret(passwordEncoder().encode(CLIENT_SECRET))
                .resourceIds(RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .authorities(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_USER.name())
                .scopes(SCOPES);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .approvalStore(approvalStore())
                .authorizationCodeServices(authorizationCodeServices())
                .userDetailsService(basicUserDetailsService)
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore());
    }
}
