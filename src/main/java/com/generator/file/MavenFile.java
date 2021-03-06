package com.generator.file;

import com.generator.CrudGenerator;
import com.generator.file.maven.BasicMavenDependency;
import com.generator.file.maven.MavenDependency;
import com.generator.file.maven.MavenInfo;
import com.generator.file.maven.MavenProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;

public class MavenFile {

    private final Set<MavenDependency> dependencies;

    private final MavenInfo mavenInfo;

    private final MavenProperties mavenProperties;

    public MavenFile() {
        this.dependencies = new LinkedHashSet<>();
        this.mavenInfo = new MavenInfo();
        this.mavenProperties = new MavenProperties();
    }

    public void generate(Path pathToCode) throws IOException {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringBuilder.append("""
                <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                \txsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                """);
        stringBuilder.append("\t<modelVersion>4.0.0</modelVersion>");

        stringBuilder.append("""
                \t<parent>
                \t\t<groupId>org.springframework.boot</groupId>
                \t\t<artifactId>spring-boot-starter-parent</artifactId>
                \t\t<version>2.3.2.RELEASE</version>
                \t\t<relativePath/>
                \t</parent>
                """);

        stringBuilder.append(mavenInfo.toXML()).append("\n");
        stringBuilder.append(mavenProperties.toXML());

        stringBuilder.append("\t").append("<dependencies>").append("\n");
        dependencies.forEach(d -> stringBuilder.append(d.toXML()));
        stringBuilder.append("""
                \t\t<dependency>
                    \t\t<groupId>org.springframework.boot</groupId>
                    \t\t<artifactId>spring-boot-starter-test</artifactId>
                    \t\t<scope>test</scope>
                    \t\t<exclusions>
                        \t\t<exclusion>
                            \t\t<groupId>org.junit.vintage</groupId>
                            \t\t<artifactId>junit-vintage-engine</artifactId>
                        \t\t</exclusion>
                    \t\t</exclusions>
                \t\t</dependency>
                """);
        stringBuilder.append("\t").append("</dependencies>").append("\n");

        stringBuilder.append("""
                    \t<build>
                        \t<plugins>
                            \t<plugin>
                                \t<groupId>org.springframework.boot</groupId>
                                \t<artifactId>spring-boot-maven-plugin</artifactId>
                            \t</plugin>
                        \t</plugins>
                    \t</build>""");

        stringBuilder.append("\n</project>");

        var newFile = CrudGenerator.PATH_TO_GENERATED_FOLDER.resolve("pom.xml");
        Files.write(newFile, stringBuilder.toString().getBytes());
    }

    public void addBasicMavenDependency(BasicMavenDependency basicMavenDependency) {
        dependencies.add(new MavenDependency().addBasicDependency(basicMavenDependency));
    }

    public void addInfo(String key, String value) {
        mavenInfo.addInfo(key, value);
    }

    public void addProperty(String key, String value) {
        mavenProperties.addProperty(key, value);
    }
}
