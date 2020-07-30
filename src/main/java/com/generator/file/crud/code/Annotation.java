package com.generator.file.crud.code;

import com.generator.file.crud.CrudCode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Annotation implements CrudCode {

    private final String name;

    private final Map<String, String> parameters;

    public Annotation(String name) {
        this.name = name;
        this.parameters = new LinkedHashMap<>();
    }

    @Override
    public String toCode() {
        var builder = new StringBuilder();
        builder.append("@").append(name);
        if (parameters.size() > 0) {
            builder.append("(");
            var array = new ArrayList<>(parameters.keySet());
            parameters.forEach((k, v) -> {
                builder.append(k).append(" ").append("=").append(" ").append(v);
                if (array.indexOf(k) < parameters.size() - 1) {
                    builder.append(",").append(" ");
                }
            });
            builder.append(")");
        }
        return builder.toString();
    }

    public void addParameter(String key, String value) {
        parameters.put(key, value);
    }
}
