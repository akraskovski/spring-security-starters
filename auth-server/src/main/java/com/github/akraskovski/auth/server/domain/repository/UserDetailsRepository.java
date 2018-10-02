package com.github.akraskovski.auth.server.domain.repository;

import com.github.akraskovski.auth.server.domain.model.BestUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring data JPA {@link BestUserDetails} repository.
 */
public interface UserDetailsRepository extends JpaRepository<BestUserDetails, String> {

    /**
     * Searches {@link BestUserDetails} full-matches for a given username.
     *
     * @param username input param
     * @return found object or null if not exists
     */
    Optional<BestUserDetails> findByEmail(String username);
}
