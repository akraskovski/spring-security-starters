package com.github.akraskovski.resource.server.remote.domain.repository;

import com.github.akraskovski.resource.server.remote.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data access repository for the {@link Company} model.
 */
public interface CompanyRepository extends JpaRepository<Company, String> {
}
