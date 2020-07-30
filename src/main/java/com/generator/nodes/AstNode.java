package com.generator.nodes;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;

public abstract class AstNode {
    public abstract void generate(CrudTree tree, MavenFile mavenFile);
}
