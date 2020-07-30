package com.generator.file.crud.code;

import com.generator.file.crud.CrudCode;

public class Setter implements CrudCode {

    private final String className;

    private final String name;

    private final String type;

    public Setter(String className, String name, String type) {
        this.className = className;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toCode() {
        return "\t" + "public" + " " + className + " " + "set" + name.substring(0, 1).toUpperCase() + name.substring(1) +
                "(" + type + " " + name + ")" + " " + "{" + "\n" + "\t\t" + "this." + name + " = " + name + ";" + "\n"
                + "\t\treturn this;" + "\n" + "\t" + "}" + "\n";
    }
}
