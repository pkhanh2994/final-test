package com.finaltest.app.service;

import com.finaltest.app.entity.Tutorial;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelService {

    public void save(MultipartFile file);

    List<Tutorial> getAllTutorials();
}
