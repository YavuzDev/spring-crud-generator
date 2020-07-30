package com.generator.file.crud;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;

public class CrudDirectory {

    private final CrudDirectory parentDirectory;

    private final String directoryName;

    private final LinkedHashSet<CrudDirectory> directories;

    private final Set<CrudFile> files;

    public CrudDirectory(CrudDirectory parentDirectory, String directoryName) {
        this.parentDirectory = parentDirectory;
        this.directoryName = directoryName;
        this.directories = new LinkedHashSet<>();
        this.files = new LinkedHashSet<>();
    }

    public void addDirectory(CrudDirectory crudDirectory) {
        directories.add(crudDirectory);
    }

    public void createFiles(Path path) throws IOException {
        var newDirectory = path.resolve(directoryName);
        if (files.size() > 0 || directories.size() > 0) {
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
    }

    public void addFile(CrudFile file) {
        files.add(file);
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public CrudDirectory getParentDirectory() {
        return parentDirectory;
    }

    public Set<CrudDirectory> getDirectories() {
        return directories;
    }

    public Set<CrudFile> getFiles() {
        return files;
    }

    public String getImportPath() {
        var builder = new StringBuilder();
        var directory = this;
        while (directory != null) {
            builder.append(directory.directoryName).append(".");
            directory = directory.parentDirectory;
        }
        var words = builder.toString().split("\\.");

        var correctBuilder = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            correctBuilder.append(words[i]);
            if (i != 0) {
                correctBuilder.append(".");
            }
        }
        return correctBuilder.toString();
    }
}
