package com.cur.furniture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cur.furniture.database.entity.Category;
import com.cur.furniture.database.entity.Furniture;
import com.cur.furniture.database.repository.FurnitureRepository;
import com.cur.furniture.dto.FurnitureCreateDto;
import com.cur.furniture.dto.FurnitureFilterDto;
import com.cur.furniture.dto.FurnitureReadDto;
import com.cur.furniture.mapper.FurnitureMapper;

import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;
    private final FurnitureMapper furnitureMapper;

    public PagedModel<FurnitureReadDto> findByFilter(FurnitureFilterDto filterDto, Pageable page) {
        List<Specification<Furniture>> specifications = new ArrayList<>();
        if (filterDto.getCategoryId() != null) {
            specifications.add((root, query, cb) -> {
                Join<Category, Furniture> join = root.join("category");
                return cb.equal(join.get("id"), filterDto.getCategoryId());
            });
        }
        Page<Furniture> result = furnitureRepository.findAll(Specification.allOf(specifications), page);
        return new PagedModel<>(result.map(furnitureMapper::toReadDto));
    }

    @Transactional
    public void create(FurnitureCreateDto createDto) {
        Furniture furniture = furnitureMapper.toEntity(createDto);
        furnitureRepository.save(furniture);
    }

}
