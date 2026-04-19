package com.cur.furniture.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cur.furniture.service.CategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
            .body(categoryService.findAll());
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok()
            .body(categoryService.findAll());
    }

}
