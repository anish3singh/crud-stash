package io.unbxd.crudstash.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;
import java.util.Set;

@Slf4j
public class PropertiesLoader {

    private Properties properties;
    public static String ENV_PREFIX = "feed.";

    public PropertiesLoader(String filePath) {
        properties = new Properties();
        try {
            InputStream inputStream = PropertiesLoader.class
                    .getClassLoader().getResourceAsStream(filePath);
            properties.load(inputStream);

            // Properties from System
            Properties systemProperties = System.getProperties();
            for (String key : systemProperties.stringPropertyNames()) {
                String value = systemProperties.getProperty(key);
                log.info("Reading system property key:" + key + " with value:" + value);
                properties.setProperty(key, value);
            }
            // Properties from env
            Set<String> keys = properties.stringPropertyNames();
            for (String key : keys) {
                String value = readProperty(properties, key);
                properties.put(key, value);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public Properties getProperties() {
        return properties;
    }

    private static String readProperty(Properties properties, String key) {
        //check if env var present
        if (System.getenv().containsKey(ENV_PREFIX + key)) {
            String value = System.getenv(ENV_PREFIX + key);
            log.info("Overriding key:" + key + " with value:" + value);
            return value;
        }
        return properties.getProperty(key);
    }
}
