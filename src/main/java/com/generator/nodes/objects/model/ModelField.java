package com.generator.nodes.objects.model;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.file.crud.code.Annotation;
import com.generator.file.crud.code.Field;
import com.generator.file.crud.code.Imports;
import com.generator.nodes.AstNode;
import com.generator.nodes.Type;

public class ModelField extends AstNode {

    private final String type;

    private final String name;

    private final String collection;

    public ModelField(String type, String name, String collection) {
        this.type = type;
        this.name = name;
        this.collection = collection;
    }

    @Override
    public void generate(CrudTree tree, MavenFile mavenFile) {
        var typeToCheck = Type.getValue(type);

        if (!collection.isBlank()) {
            var field = new Field(type + "<" + collection + ">", name, false);
            if (typeToCheck == null) {
                var file = tree.getFile(collection);
                tree.getCurrentFile().addImport(new Imports(file.getLocation().getLocation() + "." + collection));

                var annotation = new Annotation("OneToMany");
                annotation.addParameter("cascade", "CascadeType.ALL");
                field.addAnnotation(annotation);
            }
            tree.getCurrentFile().addField(field);
            switch (type) {
                case "List" -> tree.getCurrentFile().addImport(new Imports("java.util.List"));
                case "Set" -> tree.getCurrentFile().addImport(new Imports("java.util.Set"));
                default -> throw new RuntimeException("No collection with type " + type + " found");
            }
            return;
        }
        if (typeToCheck == null) {
            var field = new Field(type, name, false);
            var annotation = new Annotation("OneToOne");
            annotation.addParameter("cascade", "CascadeType.ALL");
            field.addAnnotation(annotation);
            tree.getCurrentFile().addField(field);

            var file = tree.getFile(type);
            tree.getCurrentFile().addImport(new Imports(file.getLocation().getLocation() + "." + type));
        } else {
            tree.getCurrentFile().addField(new Field(Type.getValue(type).getValue(), name, false));
        }
    }
}
