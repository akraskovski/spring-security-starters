package com.github.akraskovski.spring.oauth0;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot bootstrap class.
 */
@SpringBootApplication
public class Application {

    /**
     * Startup main method.
     *
     * @param args Input arguments
     */
    public static void main(final String... args) {
        SpringApplication.run(Application.class, args);
    }
}
