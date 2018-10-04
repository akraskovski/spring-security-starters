package com.github.akraskovski.basic.security.db.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Postgres 10 crashes on usual 'user's table.
 */
@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private String id;

    @Column(unique = true)
    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Set<Authority> authorities;

}
