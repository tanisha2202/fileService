package com.fileservice.fileservice.service;

import com.fileservice.fileservice.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {
    File store(MultipartFile file);
    List<File> getAllFiles();
}
