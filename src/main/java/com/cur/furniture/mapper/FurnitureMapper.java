package com.cur.furniture.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.entity.Category;
import com.cur.furniture.database.entity.Furniture;
import com.cur.furniture.database.repository.CategoryRepository;
import com.cur.furniture.dto.FurnitureCreateDto;
import com.cur.furniture.dto.FurniturePatchDto;
import com.cur.furniture.dto.FurnitureReadDto;
import com.cur.furniture.dto.FurnitureUpdateDto;

@Mapper(componentModel = "spring")
public abstract class FurnitureMapper {

    @Autowired protected CategoryRepository categoryRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "mapCategory")
    @Mapping(target = "image", ignore = true)
    public abstract Furniture toEntity(FurnitureCreateDto createDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "image", ignore = true)
    public abstract void update(@MappingTarget Furniture furniture, FurnitureUpdateDto updateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "image", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Furniture furniture, FurniturePatchDto updateDto);

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

    public <T> T unwrap(JsonNullable<T> jsonNullable) {
        return !jsonNullable.isPresent() ? null : jsonNullable.orElse(null);
    }

    @Condition
    public static <T> boolean isPresent(JsonNullable<T> jsonNullable) {
        return jsonNullable != null && jsonNullable.isPresent();
    }

}
