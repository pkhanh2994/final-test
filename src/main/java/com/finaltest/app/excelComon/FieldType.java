package com.finaltest.app.excelComon;

import lombok.Getter;

@Getter
public enum FieldType {

    LONG("long"),
    STRING("String"),
    BOOLEAN("boolean");

    public final String typeValue;

    FieldType(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getName() {
        return name();
    }

    public String getValue() {
        return typeValue;
    }

    @Override
    public String toString() {
        return name();
    }
}
