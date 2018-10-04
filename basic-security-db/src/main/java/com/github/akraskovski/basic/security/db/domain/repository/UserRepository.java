package com.github.akraskovski.basic.security.db.domain.repository;

import com.github.akraskovski.basic.security.db.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring data JPA {@link com.github.akraskovski.basic.security.db.domain.model.User} repository.
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Searches {@link User} full-matches for a given username.
     *
     * @param username input param
     * @return found object or null if not exists
     */
    Optional<User> findByUsername(String username);
}
