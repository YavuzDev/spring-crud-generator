package com.generator.nodes.objects.model;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.file.crud.ClassType;
import com.generator.file.crud.CrudFile;
import com.generator.file.crud.code.Annotation;
import com.generator.file.crud.code.Imports;
import com.generator.file.crud.code.Location;
import com.generator.nodes.AstNode;

import java.util.List;
import java.util.Objects;

public class ModelDeclaration extends AstNode {

    private final String name;

    private final ModelKey modelKey;

    private final List<ModelField> modelFields;

    private final List<ModelRepo> repoFunctions;

    public ModelDeclaration(String name, ModelKey modelKey, List<ModelField> modelFields, List<ModelRepo> repoFunctions) {
        this.name = name;
        this.modelKey = modelKey;
        this.modelFields = modelFields;
        this.repoFunctions = repoFunctions;
    }

    @Override
    public void generate(CrudTree tree, MavenFile mavenFile) {
        var directory = tree.getDirectory("model");

        var location = new Location(directory.getImportPath());

        var file = new CrudFile(ClassType.CLASS, location, name, "");
        file.addImport(new Imports("javax.persistence.*"));
        file.addAnnotation(new Annotation("Entity"));

        directory.addFile(file);

        tree.setCurrentDirectory(directory);
        tree.setCurrentFile(file);

        modelKey.generate(tree, mavenFile);
        modelFields.forEach(m -> m.generate(tree, mavenFile));
        if (repoFunctions != null) {
            repoFunctions.stream().filter(Objects::nonNull).forEach(m -> m.generate(tree, mavenFile));
        }
    }
}
