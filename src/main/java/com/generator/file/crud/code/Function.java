package com.generator.file.crud.code;

import com.generator.file.crud.CrudCode;

import java.util.LinkedHashSet;
import java.util.Set;

public class Function implements CrudCode {

    private final String functionName;

    private final String type;

    private final Set<Annotation> annotations;

    private final Set<Parameter> parameters;

    private final boolean isInterfaceFunction;

    public Function(String functionName, String type, boolean isInterfaceFunction) {
        this.functionName = functionName;
        this.type = type;
        this.isInterfaceFunction = isInterfaceFunction;
        this.parameters = new LinkedHashSet<>();
        this.annotations = new LinkedHashSet<>();
    }

    @Override
    public String toCode() {
        var builder = new StringBuilder();
        annotations.forEach(a -> builder.append(a.toCode()));
        builder.append("\t").append("public").append(" ").append(type).append(" ").append(functionName);
        builder.append("(");
        parameters.forEach(p -> builder.append(p.toCode()));
        builder.append(")");
        if (isInterfaceFunction) {
            builder.append(";").append("\n");
            return builder.toString();
        }
        builder.append(" ").append("{").append("\n");
        builder.append("\t").append("}").append("\n");
        return builder.toString();
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }
}
