package com.generator.file.crud.code;

import com.generator.file.crud.CrudCode;

public class Location implements CrudCode {

    private final String location;

    public Location(String location) {
        this.location = location;
    }

    @Override
    public String toCode() {
        return "package " + location + ";" + "\n\n";
    }
}
