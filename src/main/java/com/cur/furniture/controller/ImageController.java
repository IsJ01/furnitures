package com.cur.furniture.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/furnitures/images")
public class ImageController {

    private final String IMAGES_DIR = "uploads/images/furnitures/";

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getCategoryImage(@PathVariable Long id) {
        try {
            Path imagePath = Paths.get(IMAGES_DIR + id + ".jpg");
            Resource resource = new UrlResource(imagePath.toUri());
            
            if (!resource.exists()) {
                imagePath = Paths.get(IMAGES_DIR + id + ".png");
                resource = new UrlResource(imagePath.toUri());
                
                if (!resource.exists()) {
                    imagePath = Paths.get(IMAGES_DIR + "default.jpg");
                    resource = new UrlResource(imagePath.toUri());
                }
            }
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, getContentType(imagePath))
                    .body(resource);
                    
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    private String getContentType(Path path) {
        String fileName = path.toString().toLowerCase();
        if (fileName.endsWith(".png")) return MediaType.IMAGE_PNG_VALUE;
        if (fileName.endsWith(".webp")) return "image/webp";
        return MediaType.IMAGE_JPEG_VALUE;
    }
}
