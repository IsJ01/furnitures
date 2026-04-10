package com.cur.furniture.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cur.furniture.database.entity.Furniture;
import com.cur.furniture.database.repository.FurnitureRepository;
import com.cur.furniture.dto.FurnitureCreateDto;
import com.cur.furniture.mapper.FurnitureMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;
    private final FurnitureMapper furnitureMapper;

    @Transactional
    public void create(FurnitureCreateDto createDto) {
        Furniture furniture = furnitureMapper.toEntity(createDto);
        furnitureRepository.save(furniture);
    }

}
