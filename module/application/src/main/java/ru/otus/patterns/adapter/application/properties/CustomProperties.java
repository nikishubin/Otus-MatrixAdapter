package ru.otus.patterns.adapter.application.properties;

import java.io.IOException;
import java.util.Properties;

final class CustomProperties {
    private static CustomProperties instance;

    private final Properties properties;

    private CustomProperties(Properties properties) {
        this.properties = properties;
    }

    public static CustomProperties getInstance(String fileName) throws IOException {
        if (instance == null) {
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));

            instance = new CustomProperties(properties);
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }
}
