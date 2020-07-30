package com.generator.file.crud.code;

import com.generator.file.crud.CrudCode;

public class Imports implements CrudCode {

    private final String importClass;

    public Imports(String importClass) {
        this.importClass = importClass;
    }

    @Override
    public String toCode() {
        return "import " + importClass + ";";
    }
}
