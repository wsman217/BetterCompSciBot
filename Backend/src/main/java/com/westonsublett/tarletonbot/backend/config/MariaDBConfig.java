package com.westonsublett.tarletonbot.backend.config;

import com.westonsublett.tarletonbot.backend.BackendApplication;
import org.mariadb.r2dbc.MariadbConnectionConfiguration;
import org.mariadb.r2dbc.MariadbConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * This class is used to establish a connection to the database using values from the config.json.
 */
@Configuration
@EnableR2dbcRepositories
public class MariaDBConfig extends AbstractR2dbcConfiguration {

    private final Config config = BackendApplication.getConfig();

    @Override
    @Bean
    public MariadbConnectionFactory connectionFactory() {
        return new MariadbConnectionFactory(MariadbConnectionConfiguration.builder()
                .host(config.getHost())
                .port(config.getPort())
                .username(config.getUsername())
                .password(config.getPassword())
                .database(config.getDatabase())
                .build());
    }
}
