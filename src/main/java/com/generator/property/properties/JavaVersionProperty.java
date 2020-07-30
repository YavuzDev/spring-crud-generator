package com.generator.property.properties;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.property.Property;

public class JavaVersionProperty extends Property {
    @Override
    public void apply(CrudTree tree, MavenFile mavenFile, String value) {
        if (Integer.parseInt(value) < 11) {
            throw new IllegalArgumentException("Minimum java version is 11");
        }
        mavenFile.getMavenProperties().addProperty(property(), value);
    }

    @Override
    public String property() {
        return "java.version";
    }
}
