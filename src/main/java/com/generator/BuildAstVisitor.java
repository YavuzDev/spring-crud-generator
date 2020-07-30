package com.generator;

import com.generator.nodes.AstNode;

public class BuildAstVisitor extends CrudGeneratorBaseVisitor<AstNode> {

    @Override
    public AstNode visitCompileUnit(CrudGeneratorParser.CompileUnitContext ctx) {
        return super.visitCompileUnit(ctx);
    }

    @Override
    public AstNode visitFunctionDecl(CrudGeneratorParser.FunctionDeclContext ctx) {
        return super.visitFunctionDecl(ctx);
    }

    @Override
    public AstNode visitParameterDecl(CrudGeneratorParser.ParameterDeclContext ctx) {
        return super.visitParameterDecl(ctx);
    }

    @Override
    public AstNode visitBlockStmt(CrudGeneratorParser.BlockStmtContext ctx) {
        return super.visitBlockStmt(ctx);
    }

    @Override
    public AstNode visitVarDeclStmt(CrudGeneratorParser.VarDeclStmtContext ctx) {
        return super.visitVarDeclStmt(ctx);
    }

    @Override
    public AstNode visitAssignVarStmt(CrudGeneratorParser.AssignVarStmtContext ctx) {
        return super.visitAssignVarStmt(ctx);
    }

    @Override
    public AstNode visitReturnStmt(CrudGeneratorParser.ReturnStmtContext ctx) {
        return super.visitReturnStmt(ctx);
    }

    @Override
    public AstNode visitIfStmt(CrudGeneratorParser.IfStmtContext ctx) {
        return super.visitIfStmt(ctx);
    }

    @Override
    public AstNode visitWhileStmt(CrudGeneratorParser.WhileStmtContext ctx) {
        return super.visitWhileStmt(ctx);
    }

    @Override
    public AstNode visitExprStmt(CrudGeneratorParser.ExprStmtContext ctx) {
        return super.visitExprStmt(ctx);
    }

    @Override
    public AstNode visitBlockStatement(CrudGeneratorParser.BlockStatementContext ctx) {
        return super.visitBlockStatement(ctx);
    }

    @Override
    public AstNode visitVariableExpr(CrudGeneratorParser.VariableExprContext ctx) {
        return super.visitVariableExpr(ctx);
    }

    @Override
    public AstNode visitDoubleExpr(CrudGeneratorParser.DoubleExprContext ctx) {
        return super.visitDoubleExpr(ctx);
    }

    @Override
    public AstNode visitInfixExpr(CrudGeneratorParser.InfixExprContext ctx) {
        return super.visitInfixExpr(ctx);
    }

    @Override
    public AstNode visitIntExpr(CrudGeneratorParser.IntExprContext ctx) {
        return super.visitIntExpr(ctx);
    }

    @Override
    public AstNode visitFuncExpr(CrudGeneratorParser.FuncExprContext ctx) {
        return super.visitFuncExpr(ctx);
    }

    @Override
    public AstNode visitBooleanExpr(CrudGeneratorParser.BooleanExprContext ctx) {
        return super.visitBooleanExpr(ctx);
    }

    @Override
    public AstNode visitLogicalExpr(CrudGeneratorParser.LogicalExprContext ctx) {
        return super.visitLogicalExpr(ctx);
    }

    @Override
    public AstNode visitParensExpr(CrudGeneratorParser.ParensExprContext ctx) {
        return super.visitParensExpr(ctx);
    }

    @Override
    public AstNode visitCompareExpr(CrudGeneratorParser.CompareExprContext ctx) {
        return super.visitCompareExpr(ctx);
    }
}
