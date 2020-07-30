package com.generator.property;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;

public abstract class Property {

    public abstract void apply(CrudTree tree, MavenFile mavenFile, String value);

    public abstract String property();
}
