package com.cur.furniture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.entity.Category;
import com.cur.furniture.database.entity.Furniture;
import com.cur.furniture.database.repository.CategoryRepository;
import com.cur.furniture.dto.FurnitureCreateDto;
import com.cur.furniture.dto.FurnitureReadDto;

@Mapper(componentModel = "spring")
public abstract class FurnitureMapper {

    @Autowired protected CategoryRepository categoryRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "mapCategory")
    public abstract Furniture toEntity(FurnitureCreateDto createDto);

    @Mapping(target = "categoryId", source = "category", qualifiedByName = "mapCategoryId")
    public abstract FurnitureReadDto toReadDto(Furniture furniture);

    @Named("mapCategory")
    protected Category mapCategory(Long id) {
        return categoryRepository.getReferenceById(id);
    }

    @Named("mapCategoryId")
    protected Long mapCategoryId(Category category) {
        if (category == null) return null;
        return category.getId();
    }

}
