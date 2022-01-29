package com.westonsublett.tarletonbot.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.westonsublett.tarletonbot.backend.config.Config;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@SpringBootApplication
public class BackendApplication {

    @Getter
    private static final Logger logger = LoggerFactory.getLogger(BackendApplication.class);

    public static void main(String[] args) {
        new BackendApplication();
        SpringApplication.run(BackendApplication.class, args);
    }

    public BackendApplication() {
        if (Config.isFirstLoad()) {
            logger.info("\n\n\nThe application has detected that this is the first time running the application.\n" +
                    "New files will be created at this time, stopping application so config can be edited.\n\n\n");
            Config.createFirstTime();
            System.exit(0);
        }
        Config.loadConfig();
    }
}
