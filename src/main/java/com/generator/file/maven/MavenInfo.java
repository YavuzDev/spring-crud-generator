package com.generator.file.maven;

import com.generator.file.MavenGenerator;

import java.util.LinkedHashMap;
import java.util.Map;

public class MavenInfo implements MavenGenerator {

    private final Map<String, String> info;

    public MavenInfo() {
        this.info = new LinkedHashMap<>();
    }

    @Override
    public String toXML() {
        var builder = new StringBuilder();
        info.forEach((k, v) -> builder.append("\t<").append(k).append(">").append(v).append("</").append(k).append(">").append("\n"));
        return builder.toString();
    }

    public void addInfo(String key, String value) {
        info.put(key, value);
    }
}
