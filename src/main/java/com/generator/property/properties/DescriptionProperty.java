package com.generator.property.properties;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.property.Property;

public class DescriptionProperty extends Property {
    @Override
    public void apply(CrudTree tree, MavenFile mavenFile, String value) {
        mavenFile.addInfo(property(), value);
    }

    @Override
    public String property() {
        return "description";
    }
}
