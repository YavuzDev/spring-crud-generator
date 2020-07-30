package com.generator.nodes;

public enum Type {
    STRING("String"),
    INT("int"),
    LONG("long");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getValue(String identifier) {
        return Type.valueOf(identifier.toUpperCase()).getValue();
    }
}
