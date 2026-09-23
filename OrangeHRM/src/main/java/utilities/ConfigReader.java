package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            // Decoupled relative path looking inside your centralized resources folder
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            System.out.println(" ERROR: Global config.properties file not found at path! " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
