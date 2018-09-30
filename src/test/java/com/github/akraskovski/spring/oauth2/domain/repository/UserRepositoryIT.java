package com.github.akraskovski.spring.oauth2.domain.repository;

import com.github.akraskovski.spring.oauth2.domain.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Integration tests for the {@link UserRepository}.
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @After
    public void after() {
        mongoTemplate.dropCollection(User.class);
    }

    @Test
    public void findByUsernameSuccessTest() {
        final User user = generateTestUser();

        final User createdUser = userRepository.save(user);
        assertNotNull(createdUser.getId());

        assertEquals(createdUser, userRepository.findById(createdUser.getId()).get());
    }

    private static User generateTestUser() {
        final User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(20));
        user.setPassword(RandomStringUtils.randomAlphabetic(20));

        return user;
    }
}