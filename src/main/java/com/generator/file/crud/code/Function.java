package com.generator.file.crud.code;

import com.generator.file.crud.CrudCode;

import java.util.LinkedHashSet;
import java.util.Set;

public class Function implements CrudCode {

    private final String functionName;

    private final String type;

    private final Set<Annotation> annotations;

    private final Set<Parameter> parameters;

    public Function(String functionName, String type) {
        this.functionName = functionName;
        this.type = type;
        this.parameters = new LinkedHashSet<>();
        this.annotations = new LinkedHashSet<>();
    }

    @Override
    public String toCode() {
        var builder = new StringBuilder();
        annotations.forEach(a -> builder.append(a.toCode()));
        builder.append("public").append(" ").append(type).append(" ").append(functionName);
        parameters.forEach(p -> builder.append(p.toCode()));
        builder.append(" ").append("{").append("\n");
        builder.append("}").append("\n");
        return builder.toString();
    }
}
