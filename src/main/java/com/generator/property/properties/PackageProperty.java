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

        var rootDirectory = new CrudDirectory(null, split[0]);
        tree.setRootDirectory(rootDirectory);

        var codeDirectory = new CrudDirectory(rootDirectory, split[1]);
        codeDirectory.addDirectory(new CrudDirectory(codeDirectory,"model"));
        codeDirectory.addDirectory(new CrudDirectory(codeDirectory,"services"));
        codeDirectory.addDirectory(new CrudDirectory(codeDirectory,"controllers"));

        tree.getRootDirectory().addDirectory(codeDirectory);

    }

    @Override
    public String property() {
        return "package";
    }
}
