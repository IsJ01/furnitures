package com.cur.furniture.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cur.furniture.database.entity.Category;
import com.cur.furniture.database.entity.Furniture;
import com.cur.furniture.database.repository.FurnitureRepository;
import com.cur.furniture.dto.FurnitureCreateDto;
import com.cur.furniture.dto.FurnitureFilterDto;
import com.cur.furniture.dto.FurniturePatchDto;
import com.cur.furniture.dto.FurnitureReadDto;
import com.cur.furniture.dto.FurnitureUpdateDto;
import com.cur.furniture.exception.FurnitureNotFoundException;
import com.cur.furniture.mapper.FurnitureMapper;

import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;
    private final FurnitureMapper furnitureMapper;

    private final ImageService imageService;

    public PagedModel<FurnitureReadDto> findByFilter(FurnitureFilterDto filterDto, Pageable page) {
        List<Specification<Furniture>> specifications = new ArrayList<>();

        if (filterDto.getCategoryId() != null) {
            specifications.add((root, query, cb) -> {
                Join<Category, Furniture> join = root.join("category");
                return cb.equal(join.get("id"), filterDto.getCategoryId());
            });
        }
        if (filterDto.getName() != null) {
            specifications.add((root, query, cb) -> {
                return cb.like(root.get("name"), "%" + filterDto.getName() + "%");
            });
        }
        if (filterDto.getPrice() != null) {
            specifications.add((root, query, cb) -> {
                return cb.lessThanOrEqualTo(root.get("price"), filterDto.getPrice());
            });
        }
        if (filterDto.getWidth() != null) {
            specifications.add((root, query, cb) -> {
                return cb.greaterThanOrEqualTo(root.get("width"), filterDto.getWidth());
            });
        }
        if (filterDto.getHeight() != null) {
            specifications.add((root, query, cb) -> {
                return cb.greaterThanOrEqualTo(root.get("height"), filterDto.getHeight());
            });
        }
        if (filterDto.getDepth() != null) {
            specifications.add((root, query, cb) -> {
                return cb.greaterThanOrEqualTo(root.get("depth"), filterDto.getDepth());
            });
        }
        if (filterDto.getMaterial() != null) {
            specifications.add((root, query, cb) -> {
                return cb.equal(root.get("material"), filterDto.getMaterial());
            });
        }

        Page<Furniture> result = furnitureRepository.findAll(Specification.allOf(specifications), page);
        return new PagedModel<>(result.map(furnitureMapper::toReadDto));
    }

    public FurnitureReadDto findById(Long id) {
        return furnitureRepository.findById(id)
            .map(furnitureMapper::toReadDto)
            .orElseThrow(() -> new FurnitureNotFoundException(id));
    }

    @Transactional
    public void create(FurnitureCreateDto createDto) throws IOException {
        Furniture furniture = furnitureMapper.toEntity(createDto);
        furnitureRepository.save(furniture);
    }

    @Transactional
    public void update(Long id, FurnitureUpdateDto updateDto) throws IOException {
        Furniture furniture = furnitureRepository.findById(id)
            .orElseThrow(() -> new FurnitureNotFoundException(id));
        furnitureMapper.update(furniture, updateDto);
        furnitureRepository.saveAndFlush(furniture);
        if (updateDto.getImage() != null) {
            furniture.setImage(imageService.saveImage(furniture.getId(), updateDto.getImage()));
        }
    }

    @Transactional
    public void update(Long id, FurniturePatchDto updateDto) throws IOException {
        Furniture furniture = furnitureRepository.findById(id)
            .orElseThrow(() -> new FurnitureNotFoundException(id));
        furnitureMapper.update(furniture, updateDto);
        furnitureRepository.saveAndFlush(furniture);
    }

    @Transactional
    public void updateImage(Long id, MultipartFile image) throws IOException {
        Furniture furniture = furnitureRepository.findById(id)
            .orElseThrow(() -> new FurnitureNotFoundException(id));
        furniture.setImage(imageService.saveImage(furniture.getId(), image));
        furnitureRepository.saveAndFlush(furniture);
    }

}
