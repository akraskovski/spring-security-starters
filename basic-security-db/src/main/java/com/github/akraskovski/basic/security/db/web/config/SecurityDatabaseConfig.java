package com.github.akraskovski.basic.security.db.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * External security {@link javax.sql.DataSource} configuration.
 * Could be used if you have different db and dao implementation for the security.
 */
@Configuration
public class SecurityDatabaseConfig {

    /**
     * Application JPA {@link DataSource} configuration.
     *
     * @return configured bean
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Generation separate connection to the DB for security purposes.
     *
     * @return configured bean.
     */
    @Bean
    @ConfigurationProperties(prefix = "app.security.datasource")
    public DataSource securityDataSource() {
        return DataSourceBuilder.create().build();
    }
}
