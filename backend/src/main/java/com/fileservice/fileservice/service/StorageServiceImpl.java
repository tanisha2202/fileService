package com.fileservice.fileservice.service;

import com.fileservice.fileservice.model.File;
import com.fileservice.fileservice.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {
    private final FileRepository fileRepository;

    public StorageServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    @Override
    public File store(MultipartFile file) {
        try {
            if (file.isEmpty()) throw new RuntimeException("Empty file.");

            // 1. Generate a consistent ID from file content

            String contentHash = DigestUtils.md5DigestAsHex(file.getBytes()); // you can use sha256 if preferred
            UUID id = UUID.nameUUIDFromBytes(contentHash.getBytes());

            // 2. Check if file with this ID already exists
            Optional<File> existing = fileRepository.findById(id);
            if (existing.isPresent()) {
                return existing.get(); // Return existing file
            }

            File blob = new File();
            blob.setId(id);
            blob.setFileName(file.getOriginalFilename());
            blob.setContentType(file.getContentType());
            blob.setFileSize(file.getSize());
            blob.setUploadTime(Instant.now());
            blob.setFileData(ByteBuffer.wrap(file.getBytes()));

            return fileRepository.save(blob);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file in Cassandra", e);
        }
    }
    public List<File> getAllFiles(){
        return fileRepository.findAll();
    }
}
