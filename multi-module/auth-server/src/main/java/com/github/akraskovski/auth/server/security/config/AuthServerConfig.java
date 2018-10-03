package com.github.akraskovski.auth.server.security.config;

import com.github.akraskovski.auth.server.domain.model.UserRole;
import lombok.RequiredArgsConstructor;
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
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
@Import(ServerSecurityConfig.class)
@RequiredArgsConstructor
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String RESOURCE_ID = "company_api";
    private static final String CLIENT_ID = "your-client-id";
    private static final String CLIENT_SECRET = "glory-to-the-heroes";
    private static final String[] SCOPES = {"do-anything"};

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    /**
     * Password encoder bean.
     *
     * @return created component
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Token store bean.
     *
     * @return created component
     */
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
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
                // Here could be two other auth grant types: "authorization_code" and "implicit"
                .authorizedGrantTypes("password", "refresh_token")
                .authorities(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_USER.name())
                .scopes(SCOPES);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}
