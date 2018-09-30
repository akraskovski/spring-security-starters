package com.github.akraskovski.spring.oauth2.domain.repository;

import com.github.akraskovski.spring.oauth2.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Data access layer for an {@link User} model.
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Finds an {@link User} by a given username param.
     *
     * @param username user's name
     * @return found user of Optional.empty if there is no user in database
     */
    Optional<User> findByUsername(String username);
}
