package com.cur.furniture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.entity.Category;
import com.cur.furniture.database.entity.Furniture;
import com.cur.furniture.database.repository.CategoryRepository;
import com.cur.furniture.dto.FurnitureCreateDto;

@Mapper(componentModel = "spring")
public abstract class FurnitureMapper {

    @Autowired protected CategoryRepository categoryRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "mapCategory")
    public abstract Furniture toEntity(FurnitureCreateDto createDto);

    @Named("mapCategory")
    protected Category mapCategory(Long id) {
        return categoryRepository.getReferenceById(id);
    }

}
