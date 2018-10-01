package com.github.akraskovski.resource.server.domain.repository;

import com.github.akraskovski.resource.server.domain.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Data access layer for an {@link UserDetails} model.
 */
public interface UserRepository extends JpaRepository<UserDetails, String> {

    /**
     * Finds an {@link UserDetails} by a given username param.
     *
     * @param username user's name
     * @return found user of Optional.empty if there is no user in database
     */
    Optional<UserDetails> findByUsername(String username);
}
