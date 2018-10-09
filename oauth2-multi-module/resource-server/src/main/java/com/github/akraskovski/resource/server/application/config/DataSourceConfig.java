package com.github.akraskovski.resource.server.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * The type Resource server data source config.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.github.akraskovski.resource.server.domain.repository")
public class DataSourceConfig {

    /**
     * Data source data source.
     *
     * @return the data source
     */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
