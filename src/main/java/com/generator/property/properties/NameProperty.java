package com.generator.property.properties;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.property.Property;

public class NameProperty extends Property {
    @Override
    public void apply(CrudTree tree, MavenFile mavenFile, String value) {
        mavenFile.getMavenInfo().addInfo(property(), value);
    }

    @Override
    public String property() {
        return "name";
    }
}
