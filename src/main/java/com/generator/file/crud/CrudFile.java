package com.generator.file.crud;

import com.generator.file.crud.code.Imports;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;

public class CrudFile {

    private final String fileName;

    private final Set<Imports> imports;

    public CrudFile(String fileName) {
        this.fileName = fileName;
        this.imports = new LinkedHashSet<>();
    }

    public void createFile(Path path) throws IOException {
        var builder = new StringBuilder();
        imports.forEach(i -> builder.append(i.toCode()).append("\n"));
        builder.append("\n");
        builder.append("public").append(" ").append("class").append(" ").append(fileName).append(" ").append(" {").append("\n");

        builder.append("}");
        Files.write(path.resolve(fileName + ".java"), builder.toString().getBytes());
    }

    public void addImport(Imports imports) {
        this.imports.add(imports);
    }

    public String getFileName() {
        return fileName;
    }
}
