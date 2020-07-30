package com.generator.nodes.objects.model;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.file.crud.ClassType;
import com.generator.file.crud.CrudDirectory;
import com.generator.file.crud.CrudFile;
import com.generator.file.crud.code.*;
import com.generator.nodes.AstNode;
import com.generator.nodes.Type;


public class ModelRepo extends AstNode {

    private final String name;

    public ModelRepo(String name) {
        this.name = name;
    }

    @Override
    public void generate(CrudTree tree, MavenFile mavenFile) {
        var currentFile = tree.getCurrentFile();
        var directory = tree.getDirectory("repositories");

        var existingFile = tree.getFile(currentFile.getFileName() + "Repository");
        if (existingFile == null) {
            var newFile = newFile(directory, currentFile);
            tree.setCurrentFile(newFile);
        } else {
            addFunction(existingFile, existingFile);
            tree.setCurrentFile(existingFile);
        }
        tree.setCurrentDirectory(directory);
    }

    private CrudFile newFile(CrudDirectory crudDirectory, CrudFile currentFile) {
        var type = Type.getValue(currentFile.getFields().iterator().next().getType());
        var crudFile = new CrudFile(ClassType.INTERFACE, new Location(crudDirectory.getFullPath()), currentFile.getFileName() + "Repository", type.getObjectValue());
        crudFile.addAnnotation(new Annotation("Repository"));
        crudFile.addImport(new Imports(currentFile.getLocation().getLocation() + "." + currentFile.getFileName()));
        crudFile.addImport(new Imports("org.springframework.stereotype.Repository"));
        crudFile.addImport(new Imports("org.springframework.data.jpa.repository.JpaRepository"));
        addFunction(currentFile, crudFile);

        crudDirectory.addFile(crudFile);

        return crudFile;
    }

    private void addFunction(CrudFile oldFile, CrudFile newFile) {
        var functionType = "";
        if (name.contains("contains")) {
            functionType = "boolean";
        } else {
            functionType = oldFile.getFileName();
        }
        var function = new Function(name, functionType, true);

        var parameterName = name.substring(name.indexOf("By")).replace("By", "");
        var correctParameterName = parameterName.substring(0, 1).toLowerCase() + parameterName.substring(1);

        var parameterType = oldFile.getFields()
                .stream()
                .filter(f -> f.getName().toLowerCase().contains(correctParameterName))
                .map(Field::getType)
                .findFirst()
                .orElse(null);

        var parameter = new Parameter(parameterType, correctParameterName);
        function.addParameter(parameter);

        newFile.addFunction(function);
    }
}
