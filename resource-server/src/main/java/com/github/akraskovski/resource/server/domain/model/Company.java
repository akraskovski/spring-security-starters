package com.github.akraskovski.resource.server.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Secured company domain model.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Company {

    @Id
    @GeneratedValue
    private String id;
    private String name;
    private boolean active;
}
