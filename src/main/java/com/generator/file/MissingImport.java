package com.generator.file;

import com.generator.file.crud.CrudFile;

import java.util.Objects;

public class MissingImport {

    private final CrudFile crudFile;

    private final String missingType;

    public MissingImport(CrudFile crudFile, String missingType) {
        this.crudFile = crudFile;
        this.missingType = missingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissingImport that = (MissingImport) o;
        return Objects.equals(crudFile, that.crudFile) &&
                Objects.equals(missingType, that.missingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crudFile, missingType);
    }

    public CrudFile getCrudFile() {
        return crudFile;
    }

    public String getMissingType() {
        return missingType;
    }
}
