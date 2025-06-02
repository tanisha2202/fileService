package com.fileservice.fileservice.controller;

import ch.qos.logback.core.model.Model;
import com.fileservice.fileservice.model.File;
import com.fileservice.fileservice.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService){
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<File> handleFileUpload(@RequestParam("file") MultipartFile file){
        File storedFile = storageService.store(file);

        return ResponseEntity.ok(storedFile);
    }

    @GetMapping("/files")
    public ResponseEntity<List<File>> getAllFiles(){
        List<File> files = storageService.getAllFiles();
        return ResponseEntity.ok(files);
    }


}
