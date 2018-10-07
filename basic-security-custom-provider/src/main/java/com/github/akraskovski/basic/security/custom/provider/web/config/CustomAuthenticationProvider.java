package com.github.akraskovski.basic.security.custom.provider.web.config;

import com.github.akraskovski.basic.security.custom.provider.web.model.ApplicationUserDetails;
import com.github.akraskovski.basic.security.custom.provider.web.model.Authority;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Custom provider for additional flexibility compared to the standard scenario using a simple {@link org.springframework.security.core.userdetails.UserDetailsService}.
 */
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final UsernamePasswordAuthenticationToken requestAuth = (UsernamePasswordAuthenticationToken) authentication;
        final String username = requestAuth.getName();
        final String password = String.valueOf(requestAuth.getCredentials());

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new BadCredentialsException("Invalid Domain User Credentials");
        }

        // In this line calling third-party authentication provider/user's storage provider occurs.
        // The result of "login" must be existing user with roles or an Exception.
        final ApplicationUserDetails userDetails = mockAuthProvider(username, password);

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    private ApplicationUserDetails mockAuthProvider(final String username, final String password) {
        final List<SimpleGrantedAuthority> authorities = Stream.of(Authority.values())
                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                .collect(Collectors.toList());

        return new ApplicationUserDetails(username, password, authorities);
    }
}
