package com.github.akraskovski.basic.security.db.web.service;

import com.github.akraskovski.basic.security.db.domain.model.User;
import com.github.akraskovski.basic.security.db.domain.repository.UserRepository;
import com.github.akraskovski.basic.security.db.web.model.ApplicationUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring Security {@link UserDetailsService} custom implementation for searching authorizing user.
 */
@Service
@Primary
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User domainUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Cannot find user with username: %s", username)));

        return new ApplicationUserDetails(domainUser);
    }
}
