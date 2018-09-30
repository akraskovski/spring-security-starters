package com.github.akraskovski.spring.oauth2.healthcheck;

import com.github.akraskovski.spring.oauth2.domain.model.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Health-check status of MondoDB driver connection.
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class MongoTemplateIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @After
    public void after() {
        mongoTemplate.dropCollection(User.class);
    }

    @Test
    public void checkMongoTemplateTest() {
        assertNotNull(mongoTemplate);

        mongoTemplate.createCollection(User.class);

        assertTrue(mongoTemplate.collectionExists(User.class));
    }
}