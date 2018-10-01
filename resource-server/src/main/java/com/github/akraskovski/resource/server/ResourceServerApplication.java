package com.github.akraskovski.resource.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class ResourceServerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }
}
