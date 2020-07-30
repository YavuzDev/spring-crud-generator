package com.generator.file.crud;

import com.generator.file.crud.code.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class CrudFile {

    private final ClassType classType;

    private final Location location;

    private final String fileName;

    private final Set<Imports> imports;

    private final Set<Annotation> annotations;

    private final Set<Function> functions;

    private final Set<Field> fields;

    private final String repoKey;

    public CrudFile(ClassType classType, Location location, String fileName, String repoKey) {
        this.classType = classType;
        this.location = location;
        this.fileName = fileName;
        this.imports = new LinkedHashSet<>();
        this.annotations = new LinkedHashSet<>();
        this.functions = new LinkedHashSet<>();
        this.fields = new LinkedHashSet<>();
        this.repoKey = repoKey;
    }

    public void createFile(Path path) throws IOException {
        var builder = new StringBuilder();
        builder.append(location.toCode());
        imports.forEach(i -> builder.append(i.toCode()).append("\n"));
        builder.append("\n");

        annotations.forEach(a -> builder.append(a.toCode()).append("\n"));

        builder.append("public").append(" ").append(classType.name().toLowerCase()).append(" ").append(fileName).append(" ");
        if (classType == ClassType.CLASS) {
            builder.append("{").append("\n").append("\n");
        } else if (classType == ClassType.INTERFACE) {
            builder.append("extends").append(" ").append("JpaRepository<").append(fileName.replace("Repository", "")).append(",")
                    .append(" ").append(repoKey).append(">").append(" ").append("{").append("\n");
        }

        var fieldList = new ArrayList<>(fields);
        for (int i = 0; i < fieldList.size(); i++) {
            var field = fieldList.get(i);
            builder.append(field.toCode()).append("\n");

            if (i + 1 < fieldList.size() && fieldList.get(i + 1).getAnnotations().size() > 0) {
                builder.append("\n");
            }
        }

        builder.append("\n");

        functions.forEach(f -> builder.append(f.toCode()).append("\n"));

        addGetter(builder);
        addSetters(builder);

        builder.append("}");
        Files.write(path.resolve(fileName + ".java"), builder.toString().getBytes());
    }

    private void addGetter(StringBuilder builder) {
        fields.forEach(f -> {
            if (f.isFinal()) {
                return;
            }
            var getter = new Getter(f.getType(), f.getName());
            builder.append(getter.toCode()).append("\n");
        });
    }

    private void addSetters(StringBuilder builder) {
        fields.forEach(f -> {
            if (f.isFinal()) {
                return;
            }
            var setter = new Setter(fileName, f.getName(), f.getType());
            builder.append(setter.toCode()).append("\n");
        });
    }

    public void addAnnotation(Annotation annotation) {
        annotations.add(annotation);
    }

    public void addImport(Imports imports) {
        this.imports.add(imports);
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public void addFunction(Function function) {
        functions.add(function);
    }

    public String getFileName() {
        return fileName;
    }

    public Location getLocation() {
        return location;
    }

    public Set<Field> getFields() {
        return fields;
    }
}
