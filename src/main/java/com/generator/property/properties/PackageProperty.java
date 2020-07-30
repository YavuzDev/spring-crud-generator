package com.generator.property.properties;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.file.crud.ClassType;
import com.generator.file.crud.CrudDirectory;
import com.generator.file.crud.CrudFile;
import com.generator.file.crud.code.Annotation;
import com.generator.file.crud.code.Imports;
import com.generator.file.crud.code.Location;
import com.generator.property.Property;

public class PackageProperty extends Property {
    @Override
    public void apply(CrudTree tree, MavenFile mavenFile, String value) {
        mavenFile.getMavenInfo().addInfo("groupId", value);

        var split = value.split("\\.");

        var rootDirectory = new CrudDirectory(null, split[0]);
        tree.setRootDirectory(rootDirectory);

        var codeDirectory = new CrudDirectory(rootDirectory, split[1]);
        addMainClass(codeDirectory);
        codeDirectory.addDirectory(new CrudDirectory(codeDirectory, "model"));
        codeDirectory.addDirectory(new CrudDirectory(codeDirectory, "repositories"));
        codeDirectory.addDirectory(new CrudDirectory(codeDirectory, "services"));
        codeDirectory.addDirectory(new CrudDirectory(codeDirectory, "controllers"));

        tree.getRootDirectory().addDirectory(codeDirectory);

    }

    private void addMainClass(CrudDirectory directory) {
        var file = new CrudFile(ClassType.CLASS, new Location(directory.getFullPath()), "Main", "");
        file.addImport(new Imports("org.springframework.boot.SpringApplication"));
        file.addImport(new Imports("org.springframework.boot.autoconfigure.SpringBootApplication"));
        file.addAnnotation(new Annotation("SpringBootApplication"));

        var bodyText = file.getBodyText();
        bodyText.append("\t").append("public").append(" ").append("static").append(" ").append("void").append(" ").append("main").append("(").append("String[]").append(" ").append("args").append(")")
                .append(" ").append("{").append("\n").append("\t").append("\t").append("SpringApplication").append(".").append("run").append("(").append("Main")
                .append(".class").append(",").append(" ").append("args").append(")").append(";").append("\n").append("\t").append("}").append("\n");

        directory.addFile(file);
    }

    @Override
    public String property() {
        return "package";
    }
}
