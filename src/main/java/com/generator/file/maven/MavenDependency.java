package com.generator.file.maven;

import com.generator.file.MavenGenerator;

import java.util.LinkedHashMap;
import java.util.Map;

public class MavenDependency implements MavenGenerator {

    private final Map<String, String> dependencyInfo;

    public MavenDependency() {
        this.dependencyInfo = new LinkedHashMap<>();
    }

    @Override
    public String toXML() {
        var builder = new StringBuilder();
        builder.append("\t").append("\t").append("<dependency>").append("\n");
        dependencyInfo.forEach((k, v) -> builder.append("\t").append("\t").append("\t").append("<").append(k).append(">").append(v).append("</").append(k).append(">").append("\n"));
        builder.append("\t").append("\t").append("</dependency>").append("\n");
        return builder.toString();
    }

    public MavenDependency addBasicDependency(BasicMavenDependency basicMavenDependency) {
        dependencyInfo.put("groupId", basicMavenDependency.getGroupId());
        dependencyInfo.put("artifactId", basicMavenDependency.getArtifactId());
        if (basicMavenDependency.getScope() != null) {
            dependencyInfo.put("scope", basicMavenDependency.getScope());
        }
        return this;
    }
}
