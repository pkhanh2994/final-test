package com.finaltest.app.serviceImp;

import com.finaltest.app.entity.Tutorial;
import com.finaltest.app.excelComon.ExcelField;
import com.finaltest.app.excelComon.ExcelFieldMapper;
import com.finaltest.app.excelComon.ExcelFileReader;
import com.finaltest.app.excelComon.ExcelSection;
import com.finaltest.app.helper.ExcelHelper;
import com.finaltest.app.repository.TutorialRepository;
import com.finaltest.app.service.ExcelService;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExcelServiceImp implements ExcelService {

    @Autowired
    TutorialRepository repository;
    public void save(MultipartFile file) {
        try {
            List<Tutorial> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<Tutorial> getAllTutorials() {
        return repository.findAll();
    }

    public List<Tutorial> readFileExcelGeneric(MultipartFile file) {
        List<Tutorial>tutorials;
        try {
            Workbook workbook = ExcelFileReader.readExcel(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Map<String, List<ExcelField[]>> excelRowValuesMap = ExcelFileReader.getExcelRowValues(sheet);

          tutorials = ExcelFieldMapper.getPojos(excelRowValuesMap.get(ExcelSection.TUTORIALS.getValue()),
                    Tutorial.class);
        return tutorials;
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
