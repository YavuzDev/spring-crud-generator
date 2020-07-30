package com.generator.file.crud.code;

import com.generator.file.crud.CrudCode;

public class Getter implements CrudCode {

    private final String type;

    private final String name;

    public Getter(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toCode() {
        return "\t" + "public" + " " + type + " " + "get" + name.substring(0, 1).toUpperCase() + name.substring(1) +
                "(" + ")" + " " + "{" + "\n" + "\t\t" + "return" + " " + name + ";" + "\n" + "\t" + "}" + "\n";
    }
}
