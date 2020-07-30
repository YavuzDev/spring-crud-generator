package com.generator.property.properties;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.file.crud.CrudDirectory;
import com.generator.property.Property;

public class PackageProperty extends Property {
    @Override
    public void apply(CrudTree tree, MavenFile mavenFile, String value) {
        mavenFile.getMavenInfo().addInfo("groupId", value);

        var split = value.split("\\.");

        tree.setRootDirectory(new CrudDirectory(split[0]));
        tree.getRootDirectory().addDirectory(new CrudDirectory(split[1]));
    }

    @Override
    public String property() {
        return "package";
    }
}
