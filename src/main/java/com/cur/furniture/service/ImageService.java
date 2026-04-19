package com.cur.furniture.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    private final String IMAGES_DIR = "uploads/images/furnitures/";

    public String saveImage(Long id, MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(IMAGES_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        String extension = getFileExtension(file.getOriginalFilename());
        String fileName = id + extension;
        
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, file.getBytes());
        
        return fileName;
    }
    
    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            return ".jpg";
        }
        return filename.substring(filename.lastIndexOf("."));
    }
}
