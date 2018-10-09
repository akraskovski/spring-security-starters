package com.github.akraskovski.resource.server.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * The type Auth server data source config.
 */
@Configuration
public class AuthServerDataSourceConfig {

    /**
     * Auth server data source data source.
     *
     * @return the data source
     */
    @Bean
    @ConfigurationProperties(prefix = "auth.datasource")
    public DataSource authServerDataSource() {
        return DataSourceBuilder.create().build();
    }
}
