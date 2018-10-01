package com.github.akraskovski.resource.server.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Domain model representation of an user instance.
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class UserDetails {

    @Id
    @GeneratedValue(generator = "guid", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "guid", strategy = "com.github.akraskovski.resource.server.domain.model.IdGenerator")
    private String id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

}
