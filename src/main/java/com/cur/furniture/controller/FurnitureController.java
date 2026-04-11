package com.cur.furniture.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cur.furniture.dto.FurnitureCreateDto;
import com.cur.furniture.dto.FurnitureFilterDto;
import com.cur.furniture.dto.FurnitureReadDto;
import com.cur.furniture.service.FurnitureService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/furnitures")
@RequiredArgsConstructor
public class FurnitureController {

    private final FurnitureService furnitureService;

    @GetMapping
    public PagedModel<FurnitureReadDto> findByFilter(@ModelAttribute FurnitureFilterDto filterDto, Pageable pageable) {
        return furnitureService.findByFilter(filterDto, pageable);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid FurnitureCreateDto createDto) {
        furnitureService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
