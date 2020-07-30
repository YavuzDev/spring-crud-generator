package com.generator.property.properties;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.property.Property;

public class DatabasePasswordProperty extends Property {
    @Override
    public void apply(CrudTree tree, MavenFile mavenFile, String value) {
        tree.addProperty("spring.datasource.password", value);
    }

    @Override
    public String property() {
        return "database.password";
    }
}
