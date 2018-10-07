package com.github.akraskovski.basic.security.custom.provider.web.model;

import lombok.Getter;

/**
 * Application authorities, which can be applied to User.
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
