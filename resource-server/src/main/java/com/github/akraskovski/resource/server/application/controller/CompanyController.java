package com.github.akraskovski.resource.server.application.controller;

import com.github.akraskovski.resource.server.domain.model.Company;
import com.github.akraskovski.resource.server.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST API level for a {@link com.github.akraskovski.resource.server.domain.model.Company}.
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(final CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_PRODUCT_ADMIN')")
    public Company getById(@PathVariable Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_PRODUCT_ADMIN')")
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
