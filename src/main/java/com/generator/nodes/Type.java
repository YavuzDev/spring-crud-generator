package com.generator.nodes;

import java.util.Arrays;

public enum Type {
    STRING("String"),
    TEXT("String"),
    INT("int"),
    LONG("long");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Type getValue(String identifier) {
        return Arrays.stream(Type.values())
                .filter(t -> t.name().equalsIgnoreCase(identifier))
                .findFirst().orElse(null);
    }
}
