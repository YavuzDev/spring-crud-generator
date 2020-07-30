package com.generator.file.crud;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;

public class CrudDirectory {

    private final String directoryName;

    private final Set<CrudDirectory> directories;

    private final Set<CrudFile> files;

    public CrudDirectory(String directoryName) {
        this.directoryName = directoryName;
        this.directories = new LinkedHashSet<>();
        this.files = new LinkedHashSet<>();
    }

    public void addDirectory(CrudDirectory crudDirectory) {
        directories.add(crudDirectory);
    }

    public void createFiles(Path path) throws IOException {
        var newDirectory = path.resolve(directoryName);
        Files.createDirectory(newDirectory);
        files.forEach(f -> {
            try {
                f.createFile(newDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        directories.forEach(d -> {
            try {
                d.createFiles(newDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public Set<CrudDirectory> getDirectories() {
        return directories;
    }

    public Set<CrudFile> getFiles() {
        return files;
    }
}
