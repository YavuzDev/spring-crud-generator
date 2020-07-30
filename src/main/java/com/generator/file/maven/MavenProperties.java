package com.generator.file.maven;

import com.generator.file.MavenGenerator;

import java.util.LinkedHashMap;
import java.util.Map;

public class MavenProperties implements MavenGenerator {

    private final Map<String, String> mavenProperties;

    public MavenProperties() {
        this.mavenProperties = new LinkedHashMap<>();
    }

    @Override
    public String toXML() {
        var builder = new StringBuilder();
        builder.append("\t").append("<properties>").append("\n");
        mavenProperties.forEach((k, v) -> builder.append("\t").append("\t<").append(k).append(">").append(v).append("</").append(k).append(">").append("\n"));
        builder.append("\t").append("</properties>").append("\n").append("\n");
        return builder.toString();
    }

    public void addProperty(String key, String value) {
        mavenProperties.put(key, value);
    }
}
