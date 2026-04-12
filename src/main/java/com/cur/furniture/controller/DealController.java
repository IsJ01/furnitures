package com.cur.furniture.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cur.furniture.dto.DealCreateDto;
import com.cur.furniture.dto.DealReadDto;
import com.cur.furniture.service.DealService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    @GetMapping
    public PagedModel<DealReadDto> findByPage(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return dealService.findByPage(pageable);
    }

    @GetMapping("/{id}")
    public DealReadDto findById(@RequestParam Long id) {
        return dealService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid DealCreateDto createDto) {
        dealService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
