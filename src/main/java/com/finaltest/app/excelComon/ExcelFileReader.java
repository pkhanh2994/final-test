package com.finaltest.app.excelComon;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelFileReader {
    final static SimpleDateFormat dtf = new SimpleDateFormat("dd-MM-yyyy");

    private ExcelFileReader() {
    }

    public static Workbook readExcel(InputStream is)
            throws EncryptedDocumentException, IOException {
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(is);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Map<String, List<ExcelField[]>> getExcelRowValues(final Sheet sheet) {
        Map<String, List<ExcelField[]>> excelMap = new HashMap<>();
        Map<String, ExcelField[]> excelSectionHeaders = getExcelHeaderSections();
        int totalRows = sheet.getLastRowNum();
        excelSectionHeaders.forEach((section, excelFields) -> {
            List<ExcelField[]> excelFieldList = new ArrayList<>();
            for (int i = 1; i <= totalRows; i++) {
                Row row = sheet.getRow(i);
                ExcelField[] excelFieldArr = new ExcelField[excelFields.length];
                int k = 0;
                for (ExcelField ehc : excelFields) {
                    int cellIndex = ehc.getExcelIndex();
                    String cellType = ehc.getExcelColType();
                    Cell cell = row.getCell(cellIndex);
                    ExcelField excelField = new ExcelField();
                    excelField.setExcelColType(ehc.getExcelColType());
                    excelField.setExcelHeader(ehc.getExcelHeader());
                    excelField.setExcelIndex(ehc.getExcelIndex());
                    excelField.setPojoAttribute(ehc.getPojoAttribute());


                    if (FieldType.LONG.getValue().equalsIgnoreCase(cellType)) {
                        excelField.setExcelValue(String.valueOf(cell.getNumericCellValue()));
//                        excelField.setExcelValue(cell.getStringCellValue());
                    } else if (FieldType.STRING.getValue().equalsIgnoreCase(cellType)) {
                        excelField.setExcelValue(cell.getStringCellValue());
                    } else if (FieldType.BOOLEAN.getValue().equalsIgnoreCase(cellType)) {
                        excelField.setExcelValue(String.valueOf(cell.getBooleanCellValue()));
                    }
                    excelFieldArr[k++] = excelField;
                }
                excelFieldList.add(excelFieldArr);
            }
            excelMap.put(section, excelFieldList);
        });
        return excelMap;
    }

    private static Map<String, ExcelField[]> getExcelHeaderSections() {
        List<Map<String, List<ExcelField>>> jsonConfigMap = getExcelHeaderFieldSections();
        Map<String, ExcelField[]> jsonMap = new HashMap<>();
        jsonConfigMap.forEach(jps -> {
            jps.forEach((section, values) -> {
                ExcelField[] excelFields = new ExcelField[values.size()];
                jsonMap.put(section, values.toArray(excelFields));
            });
        });
        return jsonMap;
    }

    private static List<Map<String, List<ExcelField>>> getExcelHeaderFieldSections() {
        List<Map<String, List<ExcelField>>> jsonMap = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonConfig = new String(
                    Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("excel.json").toURI())));

            jsonMap = objectMapper.readValue(jsonConfig, new TypeReference<List<Map<String, List<ExcelField>>>>() {
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return jsonMap;
    }

}
