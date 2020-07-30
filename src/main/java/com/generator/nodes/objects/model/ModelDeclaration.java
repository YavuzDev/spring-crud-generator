package com.generator.nodes.objects.model;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.file.crud.CrudFile;
import com.generator.file.crud.code.Annotation;
import com.generator.file.crud.code.Imports;
import com.generator.file.crud.code.Location;
import com.generator.nodes.AstNode;

import java.util.List;

public class ModelDeclaration extends AstNode {

    private final String name;

    private final ModelKey modelKey;

    private final List<ModelField> modelFields;

    public ModelDeclaration(String name, ModelKey modelKey, List<ModelField> modelFields) {
        this.name = name;
        this.modelKey = modelKey;
        this.modelFields = modelFields;
    }

    @Override
    public void generate(CrudTree tree, MavenFile mavenFile) {
        var directory = tree.getDirectory("model");

        var location = new Location(directory.getFullPath());

        var file = new CrudFile(location, name);
        file.addImport(new Imports("javax.persistence.*"));
        file.addAnnotation(new Annotation("Entity"));

        directory.addFile(file);

        tree.setCurrentDirectory(directory);
        tree.setCurrentFile(file);

        modelKey.generate(tree, mavenFile);
        modelFields.forEach(m -> m.generate(tree, mavenFile));
    }
}
