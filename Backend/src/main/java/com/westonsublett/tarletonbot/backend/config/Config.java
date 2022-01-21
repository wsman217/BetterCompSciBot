package com.westonsublett.tarletonbot.backend.config;

import lombok.Getter;
import lombok.Setter;

public class Config {

    @Getter
    @Setter
    private boolean isFirstBoot;

    @Getter
    private final String host;
    @Getter
    private final int port;
    @Getter
    private final String username;
    @Getter
    private final String password;
    @Getter
    private final String database;

    public Config(boolean isFirstBoot, String host, int port, String username, String password, String database) {
        this.isFirstBoot = isFirstBoot;

        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;
    }
}
