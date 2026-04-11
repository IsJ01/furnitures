package com.cur.furniture.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.cur.furniture.database.entity.Category;
import com.cur.furniture.dto.CategoryReadDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "children", source = "children", qualifiedByName = "mapChildren")
    CategoryReadDto toReadDto(Category category);

    @Named("mapChildren")
    default List<CategoryReadDto> mapChildren(List<Category> childrens) {
        return childrens.stream()
            .map(this::toReadDto)
            .toList();
    }

}
