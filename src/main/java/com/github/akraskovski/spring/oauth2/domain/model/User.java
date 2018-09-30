package com.github.akraskovski.spring.oauth2.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * Domain model representation of user instance.
 */
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

}
