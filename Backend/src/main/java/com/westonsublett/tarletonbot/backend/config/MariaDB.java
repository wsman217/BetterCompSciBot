package com.westonsublett.tarletonbot.backend.config;

import com.westonsublett.tarletonbot.backend.BackendApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * This class is used to establish a connection to the database using values from the config.json.
 */

@Configuration
public class MariaDB {

    private final Config config = BackendApplication.getConfig();

    @Bean
    public DataSource getDataSource() {
        System.out.println("test");
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.mariadb.jdbc.Driver")
                .url("jdbc:mariadb://" + config.getHost() + ":" + config.getPort() + "/" + config.getDatabase())
                .username(config.getUsername())
                .password(config.getPassword());
        return dataSourceBuilder.build();
    }
}
