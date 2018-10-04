package com.github.akraskovski.basic.security.db.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for basic security testing purposes.
 */
@RestController
@RequestMapping("/api/v1/foo")
public class FooController {

    /**
     * Configuration file based permittedAll endpoint.
     */
    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("Success");
    }

    /**
     * Annotation based configured endpoint. Below example with usage annotations with different role/authority presentations.
     */
//    @PreAuthorize("hasAuthority('USER')") - doesn't work!
//    @PreAuthorize("hasAuthority('ROLE_USER')") - works
//    @PreAuthorize("hasRole('ROLE_USER')") - works
//    @PreAuthorize("hasRole('USER')") - works
//    @Secured("USER") - doesn't work!
    @Secured("ROLE_USER")
    @GetMapping("/user")
    public ResponseEntity<String> userEndpoint() {
        return ResponseEntity.ok("Success");
    }

    /**
     * Configuration file based permittedAll endpoint.
     */
    @GetMapping("/admin")
    public ResponseEntity<String> adminEndpoint() {
        return ResponseEntity.ok("Success");
    }
}
