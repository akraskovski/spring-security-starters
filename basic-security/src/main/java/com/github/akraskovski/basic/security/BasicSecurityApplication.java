package com.github.akraskovski.basic.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Security Basic authorization bootstrap project
 */
@SpringBootApplication
public class BasicSecurityApplication {

    /**
     * Spring Boot startup method.
     *
     * @param args input arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(BasicSecurityApplication.class, args);
    }
}
