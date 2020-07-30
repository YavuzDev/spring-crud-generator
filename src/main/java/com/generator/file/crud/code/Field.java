package com.generator.file.crud.code;

import com.generator.file.crud.CrudCode;

import java.util.LinkedHashSet;
import java.util.Set;

public class Field implements CrudCode {

    private final Set<Annotation> annotations;

    private final String type;

    private final String name;

    private final boolean isFinal;

    public Field(String type, String name, boolean isFinal) {
        this.annotations = new LinkedHashSet<>();
        this.type = type;
        this.name = name;
        this.isFinal = isFinal;
    }

    @Override
    public String toCode() {
        var builder = new StringBuilder();

        int index = 0;
        for (var annotation : annotations) {
            builder.append("\t").append(annotation.toCode());
            if (index < annotations.size() - 1) {
                builder.append("\n");
            }
            index++;
        }
        builder.append("\n").append("\t").append("private");
        if (isFinal) {
            builder.append(" ").append("final");
        }
        builder.append(" ").append(type).append(" ").append(name).append(";");
        return builder.toString();
    }

    public void addAnnotation(Annotation annotation) {
        annotations.add(annotation);
    }

    public Set<Annotation> getAnnotations() {
        return annotations;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
