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

    private final Logger logger = LoggerFactory.getLogger(BackendApplication.class);
    @Getter
    private static Config config;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    public BackendApplication() {
        if (isFirstLoad()) {
            logger.info("The application has detected that this is the first time running the application.\n" +
                    "New files will be created at this time, please close the application to change config options.");
            createFirstTime();
        }
        loadConfig();
    }

    /**
     * Check if this is the first time loading the application.
     *
     * @return true if it is the first load false otherwise.
     */
    private boolean isFirstLoad() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Path.of("../config.json"));
            Config config = gson.fromJson(reader, Config.class);
            return config.isFirstBoot();
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * If this is the first load then copy all files from inside the jar to the outside of the jar.
     */
    private void createFirstTime() {
        File config = copyFile("config.json");
        //Change the value of isFirstTime from true to false in the config.
        if (config != null) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try {
                loadConfig();
                BackendApplication.config.setFirstBoot(false);
                Writer fw = new FileWriter(config.getPath());
                gson.toJson(BackendApplication.config, fw);
                /*Some reason you have to call both flush and close even though you would think calling close would call flush
                itself but oh well.*/
                fw.flush();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Copy a file from inside the jar to outside the jar.
     *
     * @param fileName the filename in the resources' folder.
     * @return true if successful false otherwise.
     */
    private File copyFile(String fileName) {
        try {
            URL url = BackendApplication.class.getClassLoader().getResource(fileName);
            if (url == null) {
                logger.error("File:" + fileName + " could not be found please delete all files and retry.");
                return null;
            }
            File file = new File(url.toURI());

            File output = Files.copy(file.toPath(), Path.of("../" + fileName), StandardCopyOption.REPLACE_EXISTING).toFile();
            if (!output.exists())
                logger.error("File:" + fileName + " was unsuccessfully copied. Please delete all files and retry.");
            return output;
        } catch (Exception e) {
            logger.error("File:" + fileName + " was unsuccessfully copied. Please delete all files and retry.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Load the config into memory.
     */
    private void loadConfig() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            config = gson.fromJson(Files.newBufferedReader(Path.of("../config.json")), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
