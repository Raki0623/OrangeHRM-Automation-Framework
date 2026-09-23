package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        // ClassLoader locates the file directly inside the target/classes directory automatically
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException(" ERROR: config.properties file not found inside the resources directory!");
            }
            properties.load(input);
        } catch (IOException e) {
            System.out.println(" ERROR: Failed to load config.properties attributes! " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
