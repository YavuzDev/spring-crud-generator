package com.generator.file.crud.code;

import com.generator.file.crud.CrudCode;

import java.util.LinkedHashSet;
import java.util.Set;

public class Parameter implements CrudCode {

    private final Set<Annotation> annotations;

    private final String type;

    private final String name;

    public Parameter(String type, String name) {
        this.annotations = new LinkedHashSet<>();
        this.type = type;
        this.name = name;
    }

    @Override
    public String toCode() {
        var builder = new StringBuilder();
        builder.append("(");
        annotations.forEach(a -> builder.append(a.toCode()));
        builder.append(type).append(" ").append(name);
        return builder.toString();
    }
}
