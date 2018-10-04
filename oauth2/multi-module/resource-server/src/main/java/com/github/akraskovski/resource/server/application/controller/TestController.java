package com.github.akraskovski.resource.server.application.controller;

import com.github.akraskovski.resource.server.domain.model.Company;
import com.github.akraskovski.resource.server.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class TestController {

    private final CompanyRepository companyRepository;

    @Autowired
    public TestController(final CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
