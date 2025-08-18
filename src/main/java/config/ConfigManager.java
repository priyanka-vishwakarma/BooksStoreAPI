package config;

import java.io.*;
import java.util.Properties;

public class ConfigManager {

    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    private static final Properties prop = new Properties();
    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
