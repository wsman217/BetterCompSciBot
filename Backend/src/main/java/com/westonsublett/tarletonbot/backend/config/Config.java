package com.westonsublett.tarletonbot.backend.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.westonsublett.tarletonbot.backend.BackendApplication;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Config {

    private static final Logger logger = BackendApplication.getLogger();
    private static ConfigData data;

    //This gets the content root of the program. The .replace function gets rid of the folder Backend in the path so tests can run.
    private static final String path = new File("").getAbsolutePath().replace("Backend", "") + "../config.json";

    /**
     * If this is the first load then copy all files from inside the jar to the outside of the jar.
     */
    public static void createFirstTime() {
        BackendApplication.getLogger().info("\n\n\nThe application has detected that this is the first time running the application.\n" +
                "New files will be created at this time, stopping application so config can be edited.\n\n\n");

        File config = copyFile();
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
        //System.exit(0);
    }

    /**
     * Copy a file from inside the jar to outside the jar.
     *
     * @return true if successful false otherwise.
     */
    public static File copyFile() {
        try {
            URL url = BackendApplication.class.getClassLoader().getResource("config.json");
            if (url == null) {
                BackendApplication.getLogger().error("File: config.json could not be found please delete all files and retry.");
                return null;
            }
            File file = new File(url.toURI());
            System.out.println(file.getAbsolutePath());
            System.out.println(path);
            File output = Files.copy(file.toPath(), Path.of(path), StandardCopyOption.REPLACE_EXISTING).toFile();
            System.out.println(output.exists());
            if (!output.exists())
                logger.error("File: config.json was unsuccessfully copied. Please delete all files and retry.");
            return output;
        } catch (Exception e) {
            logger.error("File: config.json was unsuccessfully copied. Please delete all files and retry.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Load the config into memory.
     */
    public static boolean loadConfig() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            data = gson.fromJson(Files.newBufferedReader(Path.of(path)), ConfigData.class);
            System.out.println(path);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static ConfigData getData() {
        if (data == null)
            if (!loadConfig())
                createFirstTime();
        return data;
    }

    public static class ConfigData {
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
        @Getter
        @Setter
        private boolean isFirstBoot;


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
