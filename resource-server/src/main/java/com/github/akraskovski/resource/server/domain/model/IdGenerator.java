package com.github.akraskovski.resource.server.domain.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * Entities custom identifier generator
 */
public class IdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(final SharedSessionContractImplementor session, final Object object) throws HibernateException {
        return UUID.randomUUID().toString();
    }
}
