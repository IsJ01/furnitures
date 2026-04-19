package com.cur.furniture.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cur.furniture.database.repository.CategoryRepository;
import com.cur.furniture.dto.CategoryReadDto;
import com.cur.furniture.exception.CategoryNotFoundException;
import com.cur.furniture.mapper.CategoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryReadDto findById(Long id) {
        return categoryRepository.findById(id)
            .map(categoryMapper::toReadDto)
            .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public List<CategoryReadDto> findAll() {
        return categoryRepository.findAll().stream()
            .filter(category -> category.getParent() == null)
            .map(categoryMapper::toReadDto)
            .toList();
    }

}
