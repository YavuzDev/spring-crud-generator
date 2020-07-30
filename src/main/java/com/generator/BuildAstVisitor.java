package com.generator;

import com.generator.nodes.AstNode;
import com.generator.nodes.CompileUnitNode;
import com.generator.nodes.objects.model.ModelDeclaration;
import com.generator.nodes.objects.model.ModelField;
import com.generator.nodes.objects.model.ModelKey;
import com.generator.nodes.objects.model.ModelRepo;

import java.util.List;
import java.util.stream.Collectors;

public class BuildAstVisitor extends CrudGeneratorBaseVisitor<AstNode> {

    @Override
    public AstNode visitCompileUnit(CrudGeneratorParser.CompileUnitContext ctx) {
        var models = getModels(ctx.modelDecl());
        return new CompileUnitNode(models);
    }

    @Override
    public AstNode visitModelDecl(CrudGeneratorParser.ModelDeclContext ctx) {
        var fields = getFields(ctx.modelFields());
        var repoFunctions = getRepoFunctions(ctx.modelRepo());
        return new ModelDeclaration(ctx.name.getText(), (ModelKey) visit(ctx.key), fields, repoFunctions);
    }

    @Override
    public AstNode visitModelKey(CrudGeneratorParser.ModelKeyContext ctx) {
        return new ModelKey(ctx.type.getText());
    }

    @Override
    public AstNode visitModelFields(CrudGeneratorParser.ModelFieldsContext ctx) {
        var value = "";
        if (ctx.collection != null) {
            value = ctx.collection.getText();
        }
        return new ModelField(ctx.type.getText(), ctx.name.getText(), value);
    }

    @Override
    public AstNode visitModelRepo(CrudGeneratorParser.ModelRepoContext ctx) {
        if (ctx.name == null) {
            return super.visitModelRepo(ctx);
        }
        return new ModelRepo(ctx.name.getText());
    }

    private List<ModelRepo> getRepoFunctions(List<CrudGeneratorParser.ModelRepoContext> modelRepo) {
        return modelRepo
                .stream()
                .map(this::visit)
                .map(node -> (ModelRepo) node)
                .collect(Collectors.toList());
    }

    private List<ModelDeclaration> getModels(List<CrudGeneratorParser.ModelDeclContext> modelDecl) {
        return modelDecl
                .stream()
                .map(this::visit)
                .map(node -> (ModelDeclaration) node)
                .collect(Collectors.toList());
    }

    private List<ModelField> getFields(List<CrudGeneratorParser.ModelFieldsContext> modelFields) {
        return modelFields
                .stream()
                .map(this::visit)
                .map(node -> (ModelField) node)
                .collect(Collectors.toList());
    }
}
