package com.github.akraskovski.basic.security.custom.provider.web.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Security user model representation.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class ApplicationUserDetails extends org.springframework.security.core.userdetails.User {

    public ApplicationUserDetails(final String username, final String password, final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
