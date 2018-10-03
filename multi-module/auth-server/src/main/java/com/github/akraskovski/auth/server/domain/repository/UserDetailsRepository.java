package com.github.akraskovski.auth.server.domain.repository;

import com.github.akraskovski.auth.server.domain.model.DomainUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring data JPA {@link DomainUserDetails} repository.
 */
public interface UserDetailsRepository extends JpaRepository<DomainUserDetails, String> {

    /**
     * Searches {@link DomainUserDetails} full-matches for a given username.
     *
     * @param email input param
     * @return found object or null if not exists
     */
    Optional<DomainUserDetails> findByEmail(String email);
}
