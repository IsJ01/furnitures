package com.cur.furniture.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cur.furniture.dto.FurnitureCreateDto;
import com.cur.furniture.dto.FurnitureFilterDto;
import com.cur.furniture.dto.FurniturePatchDto;
import com.cur.furniture.dto.FurnitureReadDto;
import com.cur.furniture.dto.FurnitureUpdateDto;
import com.cur.furniture.service.FurnitureService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/furnitures")
@RequiredArgsConstructor
public class FurnitureController {

    private final FurnitureService furnitureService;

    @GetMapping
    public PagedModel<FurnitureReadDto> findByFilter(
        @ModelAttribute FurnitureFilterDto filterDto, 
        @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return furnitureService.findByFilter(filterDto, pageable);
    }

    @GetMapping("/{id}")
    public FurnitureReadDto findById(@PathVariable Long id) {
        return furnitureService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid FurnitureCreateDto createDto) throws IOException {
        furnitureService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @ModelAttribute @Valid FurnitureUpdateDto updateDto) throws IOException {
        furnitureService.update(id, updateDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FurniturePatchDto updateDto) throws IOException {
        furnitureService.update(id, updateDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestParam MultipartFile image) throws IOException {
        furnitureService.updateImage(id, image);
        return ResponseEntity.ok().build();
    }
    
}
