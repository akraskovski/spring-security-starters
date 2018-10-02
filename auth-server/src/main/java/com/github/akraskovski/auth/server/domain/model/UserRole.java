package com.github.akraskovski.auth.server.domain.model;

import lombok.Getter;

/**
 * Application roles, which can be applied to a {@link BestUserDetails}.
 */
public enum UserRole {

    ROLE_ADMIN("Administrator"),
    ROLE_USER("User");

    @Getter
    private String description;

    UserRole(final String description) {
        this.description = description;
    }
}
