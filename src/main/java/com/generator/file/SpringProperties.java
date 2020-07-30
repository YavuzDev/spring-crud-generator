package com.generator.file;

import java.util.HashMap;
import java.util.Map;

public class SpringProperties {

    private final Map<String, String> properties;

    public SpringProperties() {
        this.properties = new HashMap<>();
    }

    public byte[] toBytes() {
        var builder = new StringBuilder();
        properties.forEach((k, v) -> builder.append(k).append("=").append(v).append("\n"));
        return builder.toString().getBytes();
    }

    public void addProperty(String key, String value) {
        properties.put(key, value);
    }
}
