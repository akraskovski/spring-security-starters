package com.github.akraskovski.auth.server.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    //TODO: change to POST with body
    @GetMapping("/signUp")
    public ResponseEntity<String> signUp() {
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/me")
    public ResponseEntity<String> me() {
        return ResponseEntity.ok("Success");
    }
}
