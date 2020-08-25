package ru.otus.patterns.adapter.application.properties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class PropertiesSelector {

    private static final Map<String, CustomProperties> available = new HashMap<>();

    private PropertiesSelector() {
    }

    public static String getProperty(String path, String key) throws IOException {
        if (!available.containsKey(path)) {
            available.putIfAbsent(path, CustomProperties.getInstance(path));
        }
        return available.get(path).getProperties().getProperty(key);
    }
}
