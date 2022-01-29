package com.westonsublett.tarletonbot.backend.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.westonsublett.tarletonbot.backend.BackendApplication;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Config {

    @Getter
    private static ConfigData data;
    private static final Logger logger = BackendApplication.getLogger();

    /**
     * Check if this is the first time loading the application.
     *
     * @return true if it is the first load false otherwise.
     */
    public static boolean isFirstLoad() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Path.of("config.json"));
            ConfigData config = gson.fromJson(reader, ConfigData.class);
            return config.isFirstBoot();
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * If this is the first load then copy all files from inside the jar to the outside of the jar.
     */
    public static void createFirstTime() {
        File config = copyFile("config.json");
        //Change the value of isFirstTime from true to false in the config.
        if (config != null) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try {
                loadConfig();
                data.setFirstBoot(false);
                Writer fw = new FileWriter(config.getPath());
                gson.toJson(data, fw);
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
    public static File copyFile(String fileName) {
        try {
            URL url = BackendApplication.class.getClassLoader().getResource(fileName);
            if (url == null) {
                BackendApplication.getLogger().error("File:" + fileName + " could not be found please delete all files and retry.");
                return null;
            }
            File file = new File(url.toURI());

            File output = Files.copy(file.toPath(), Path.of(fileName), StandardCopyOption.REPLACE_EXISTING).toFile();
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
    public static void loadConfig() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            data = gson.fromJson(Files.newBufferedReader(Path.of("config.json")), ConfigData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ConfigData {
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



        public ConfigData(boolean isFirstBoot, String host, int port, String username, String password, String database) {
            this.isFirstBoot = isFirstBoot;

            this.host = host;
            this.port = port;
            this.username = username;
            this.password = password;
            this.database = database;
        }
    }
}
