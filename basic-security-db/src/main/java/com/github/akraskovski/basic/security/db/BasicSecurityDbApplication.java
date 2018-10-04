package com.github.akraskovski.basic.security.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Basic security app with jdbc connection and storing users in database.
 */
@SpringBootApplication
public class BasicSecurityDbApplication {

    /**
     * Spring Boot startup method.
     *
     * @param args input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BasicSecurityDbApplication.class, args);
    }
}
