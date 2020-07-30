package com.generator.file;

import com.generator.CrudGenerator;
import com.generator.file.crud.CrudDirectory;
import com.generator.file.crud.CrudFile;
import com.generator.file.crud.code.Imports;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;

public class CrudTree {

    private CrudDirectory rootDirectory;

    private CrudDirectory currentDirectory;

    private CrudFile currentFile;

    private final SpringProperties springProperties;

    private final Set<MissingImport> missingImports;

    public CrudTree() {
        this.springProperties = new SpringProperties();
        this.missingImports = new LinkedHashSet<>();
    }

    public void generateFiles(Path path) throws IOException {
        var correctPath = path.getParent().resolve(CrudGenerator.GENERATED_DIRECTORY);

        var srcPath = correctPath.resolve("src");
        Files.createDirectory(srcPath);

        var testPath = srcPath.resolve("test");
        Files.createDirectory(testPath);

        var testJavaPath = testPath.resolve("java");
        Files.createDirectory(testJavaPath);

        var testResourcesPath = testPath.resolve("resources");
        Files.createDirectory(testResourcesPath);

        var mainPath = srcPath.resolve("main");
        Files.createDirectory(mainPath);

        var resourcesPath = mainPath.resolve("resources");
        Files.createDirectory(resourcesPath);

        var springPropertiesFile = resourcesPath.resolve("application.properties");
        Files.write(springPropertiesFile, springProperties.toBytes());

        var javaPath = mainPath.resolve("java");
        Files.createDirectory(javaPath);

        rootDirectory.createFiles(javaPath);
    }

    public void setRootDirectory(CrudDirectory rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public CrudDirectory getRootDirectory() {
        return rootDirectory;
    }

    public void addProperty(String key, String value) {
        springProperties.addProperty(key, value);
    }

    public CrudDirectory getDirectory(String directoryName) {
        for (CrudDirectory directory : rootDirectory.getDirectories()) {
            if (directory.getDirectoryName().equalsIgnoreCase(directoryName)) {
                return directory;
            }
            for (CrudDirectory subDirectory : directory.getDirectories()) {
                if (subDirectory.getDirectoryName().equalsIgnoreCase(directoryName)) {
                    return subDirectory;
                }
            }
        }
        return null;
    }

    public CrudFile getFile(String fileName) {
        for (CrudDirectory directory : rootDirectory.getDirectories()) {
            for (CrudFile file : directory.getFiles()) {
                if (file.getFileName().equalsIgnoreCase(fileName)) {
                    return file;
                }
            }
            for (CrudDirectory subDirectory : directory.getDirectories()) {
                for (CrudFile subFile : subDirectory.getFiles()) {
                    if (subFile.getFileName().equalsIgnoreCase(fileName)) {
                        return subFile;
                    }
                }
            }
        }
        return null;
    }

    public CrudDirectory getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(CrudDirectory currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public CrudFile getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(CrudFile currentFile) {
        this.currentFile = currentFile;
    }

    public void addMissingImport(CrudFile file, String missingType) {
        missingImports.add(new MissingImport(file, missingType));
    }

    public void addMissingImports() {
        missingImports.forEach(m -> {
            var file = getFile(m.getMissingType());
            m.getCrudFile().addImport(new Imports(file.getLocation().getLocation() + "." + m.getMissingType()));
            try {
                m.getCrudFile().createFile(file.getDirectory());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
