package com.generator.nodes;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import com.generator.nodes.objects.model.ModelDeclaration;

import java.util.List;

public class CompileUnitNode extends AstNode {

    private final List<ModelDeclaration> modelDeclarations;

    public CompileUnitNode(List<ModelDeclaration> modelDeclarations) {
        this.modelDeclarations = modelDeclarations;
    }

    @Override
    public void generate(CrudTree tree, MavenFile mavenFile) {
        modelDeclarations.forEach(m -> m.generate(tree, mavenFile));
    }
}
