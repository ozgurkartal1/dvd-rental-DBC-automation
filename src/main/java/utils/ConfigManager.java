package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties prop = new Properties();
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);

    static {

        try(InputStream inputStream = ConfigManager.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            prop.load(inputStream);
            logger.info("Properties file is loaded successfully");
        } catch (IOException e) {
            logger.error("Properties file can not be loaded");
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key){
        return prop.getProperty(key);
    }
}
