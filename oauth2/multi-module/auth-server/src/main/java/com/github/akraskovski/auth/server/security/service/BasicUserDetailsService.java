package com.github.akraskovski.auth.server.security.service;

import com.github.akraskovski.auth.server.domain.model.DomainUserDetails;
import com.github.akraskovski.auth.server.domain.model.UserRole;
import com.github.akraskovski.auth.server.domain.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of the Spring Security {@link UserDetailsService}.
 */
@Service
@RequiredArgsConstructor
public class BasicUserDetailsService implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final DomainUserDetails domainUserDetails = userDetailsRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Cannot find user with username: %s", username)));

        return new User(domainUserDetails.getEmail(), domainUserDetails.getPassword(), createAuthorities(domainUserDetails.getRoles()));
    }

    private static Set<SimpleGrantedAuthority> createAuthorities(final Set<UserRole> roles) {
        return roles.parallelStream()
                .map(UserRole::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
