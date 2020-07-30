package com.generator.property.properties;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.file.maven.BasicMavenDependency;
import com.generator.property.Property;

public class DatabaseProperty extends Property {
    @Override
    public void apply(CrudTree tree, MavenFile mavenFile, String value) {
        mavenFile.addBasicMavenDependency(new BasicMavenDependency()
                .setGroupId("org.springframework.boot")
                .setArtifactId("spring-boot-starter-data-jpa"));

        switch (value.toLowerCase()) {
            case "h2":
                mavenFile.addBasicMavenDependency(new BasicMavenDependency()
                        .setGroupId("com.h2database")
                        .setArtifactId("h2")
                        .setScope("runtime"));

                tree.addProperty("spring.datasource.url", "jdbc:h2:mem:testdb");
                tree.addProperty("spring.datasource.driverClassName", "org.h2.Driver");
                tree.addProperty("spring.jpa.database-platform", "org.hibernate.dialect.H2Dialect");
                break;
            case "postgres":
            case "postgresql":
                mavenFile.addBasicMavenDependency(new BasicMavenDependency()
                        .setGroupId("org.postgresql")
                        .setArtifactId("postgresql")
                        .setScope("runtime"));

                tree.addProperty("spring.jpa.database", "POSTGRESQL");
                tree.addProperty("spring.datasource.platform", "postgres");
                break;
            default:
                throw new IllegalStateException("Unsupported database: " + value);
        }
    }

    @Override
    public String property() {
        return "database";
    }
}
