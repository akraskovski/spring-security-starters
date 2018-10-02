package com.github.akraskovski.auth.server.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
 * Domain model used for securing app.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class BestUserDetails {

    @Id
    @GeneratedValue
    private String id;
    @Column(unique = true)
    private String email;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Set<UserRole> roles;
}
