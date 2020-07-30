package com.generator;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.property.PropertyManager;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class CrudGenerator {

    public static final String GENERATED_DIRECTORY = "generated";

    private ANTLRErrorListener getErrorListener() {
        return new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                    int line, int charPositionInLine,
                                    String msg, RecognitionException e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    private CommonTokenStream runLexer(CharStream input) {
        var lexer = new CrudGeneratorLexer(input);
        lexer.addErrorListener(getErrorListener());
        return new CommonTokenStream(lexer);
    }

    private ParseTree runParser(CommonTokenStream tokens) {
        var parser = new CrudGeneratorParser(tokens);
        parser.addErrorListener(getErrorListener());
        return parser.compileUnit();
    }

    private void compile(CharStream input, PropertyManager propertyManager, Path pathToCode, Path pathToProperties) throws IOException {
        var tokens = runLexer(input);
        var parseTree = runParser(tokens);

        var buildAstVisitor = new BuildAstVisitor();

        var astNode = buildAstVisitor.visit(parseTree);

        var crudTree = new CrudTree();
        var mavenFile = new MavenFile();

        propertyManager.readProperties(pathToProperties, crudTree, mavenFile);
        astNode.generate(crudTree, mavenFile);

        mavenFile.generate(pathToCode);
        crudTree.generateFiles(pathToCode);

        crudTree.addMissingImports();
    }

    public static void main(String[] args) throws IOException {
        var generator = new CrudGenerator();

        var propertyManager = new PropertyManager();

        var pathToCode = Path.of("files").resolve("test.cg");
        var pathToProperties = Path.of("files").resolve("cg.properties");

        var directory = pathToCode.getParent().resolve(GENERATED_DIRECTORY);
        if (Files.exists(directory)) {
            Files.walk(directory).sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        Files.createDirectory(directory);

        generator.compile(CharStreams.fromPath(pathToCode), propertyManager, pathToCode, pathToProperties);
    }
}
