package com.github.akraskovski.basic.security.db.domain.model;

import lombok.Getter;

/**
 * Application roles, which can be applied to a {@link User}.
 */
public enum Authority {

    ROLE_ADMIN("Administrator"),
    ROLE_USER("User");

    @Getter
    private String description;

    Authority(final String description) {
        this.description = description;
    }
}
