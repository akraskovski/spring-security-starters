package com.github.akraskovski.basic.security.db.web.model;

import com.github.akraskovski.basic.security.db.domain.model.Authority;
import com.github.akraskovski.basic.security.db.domain.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Security user model representation.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class ApplicationUserDetails extends org.springframework.security.core.userdetails.User {

    private String userId;

    public ApplicationUserDetails(final User domainUser) {
        super(domainUser.getUsername(), domainUser.getPassword(), createAuthorities(domainUser.getAuthorities()));

        this.userId = domainUser.getId();
    }

    private static Set<SimpleGrantedAuthority> createAuthorities(final Set<Authority> roles) {
        return roles.parallelStream()
                .map(Authority::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
