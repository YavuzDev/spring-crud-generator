package com.generator.property.properties;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.property.Property;

public class DatabaseUsernameProperty extends Property {
    @Override
    public void apply(CrudTree tree, MavenFile mavenFile, String value) {
        tree.addProperty("spring.datasource.username", value);
    }

    @Override
    public String property() {
        return "database.username";
    }
}
