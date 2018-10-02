package com.github.akraskovski.auth.server.security.model;

import com.github.akraskovski.auth.server.domain.model.BestUserDetails;
import com.github.akraskovski.auth.server.domain.model.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Security user model representation.
 */
@Getter
@EqualsAndHashCode
public class ApplicationUserDetails extends org.springframework.security.core.userdetails.User {

    private String userId;

    public ApplicationUserDetails(final BestUserDetails domainUserDetails) {
        super(domainUserDetails.getEmail(), domainUserDetails.getPassword(), createAuthorities(domainUserDetails.getRoles()));

        this.userId = domainUserDetails.getId();
    }

    private static Set<SimpleGrantedAuthority> createAuthorities(final Set<UserRole> roles) {
        return roles.parallelStream()
                .map(UserRole::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
