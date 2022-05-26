package com.finaltest.app.excelComon;

public enum ExcelSection {

    TUTORIALS("Tutorials");

    final String typeValue;

    private ExcelSection(final String typeValue) {
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
