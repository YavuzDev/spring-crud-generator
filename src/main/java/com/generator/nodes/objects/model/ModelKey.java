package com.generator.nodes.objects.model;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.file.crud.code.Annotation;
import com.generator.file.crud.code.Field;
import com.generator.nodes.AstNode;
import com.generator.nodes.Type;

public class ModelKey extends AstNode {

    private final String type;

    public ModelKey(String type) {
        this.type = type;
    }

    @Override
    public void generate(CrudTree tree, MavenFile mavenFile) {
        var field = new Field(Type.getValue(type), "key", false);
        field.addAnnotation(new Annotation("Id"));

        tree.getCurrentFile().addField(field);
    }
}
