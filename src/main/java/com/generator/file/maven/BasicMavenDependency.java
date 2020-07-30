package com.generator.file.maven;

public class BasicMavenDependency {

    private String groupId;

    private String artifactId;

    private String scope;

    public String getGroupId() {
        return groupId;
    }

    public BasicMavenDependency setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public BasicMavenDependency setArtifactId(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public BasicMavenDependency setScope(String scope) {
        this.scope = scope;
        return this;
    }
}
