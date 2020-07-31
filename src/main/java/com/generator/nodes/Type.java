package com.generator.nodes;

import java.util.Arrays;

public enum Type {
    STRING("String", "String"),
    TEXT("String", "String"),
    BOOLEAN("boolean", "Boolean"),
    BYTE("byte", "Byte"),
    SHORT("short", "Short"),
    INT("int", "Integer"),
    LONG("long", "Long"),
    DOUBLE("double", "Double");

    private final String value;

    private final String objectValue;

    Type(String value, String objectValue) {
        this.value = value;
        this.objectValue = objectValue;
    }

    public String getValue() {
        return value;
    }

    public String getObjectValue() {
        return objectValue;
    }

    public static Type getValue(String identifier) {
        return Arrays.stream(Type.values())
                .filter(t -> t.name().equalsIgnoreCase(identifier))
                .findFirst().orElse(null);
    }
}
