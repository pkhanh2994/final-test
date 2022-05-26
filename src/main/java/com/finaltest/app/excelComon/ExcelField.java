package com.finaltest.app.excelComon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExcelField {

    private String excelHeader;
    private int excelIndex;
    private String excelColType;
    private String excelValue;
    private String pojoAttribute;
}
