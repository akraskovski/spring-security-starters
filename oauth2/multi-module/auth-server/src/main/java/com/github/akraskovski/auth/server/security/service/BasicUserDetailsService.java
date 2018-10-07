package com.github.akraskovski.auth.server.security.service;

import com.github.akraskovski.auth.server.domain.model.DomainUserDetails;
import com.github.akraskovski.auth.server.domain.repository.UserDetailsRepository;
import com.github.akraskovski.auth.server.security.model.ApplicationUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

        return new ApplicationUserDetails(domainUserDetails);
    }
}
