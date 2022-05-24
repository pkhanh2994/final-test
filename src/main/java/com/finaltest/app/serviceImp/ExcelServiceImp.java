package com.finaltest.app.serviceImp;

import com.finaltest.app.entity.Tutorial;
import com.finaltest.app.helper.ExcelHelper;
import com.finaltest.app.repository.TutorialRepository;
import com.finaltest.app.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
}
