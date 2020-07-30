package com.generator.nodes.objects.model;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.file.crud.code.Field;
import com.generator.nodes.AstNode;
import com.generator.nodes.Type;

public class ModelField extends AstNode {

    private final String type;

    private final String name;

    public ModelField(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public void generate(CrudTree tree, MavenFile mavenFile) {
        tree.getCurrentFile().addField(new Field(Type.getValue(type), name, false));
    }
}
