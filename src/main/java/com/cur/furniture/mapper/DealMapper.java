package com.cur.furniture.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.entity.Deal;
import com.cur.furniture.database.entity.Furniture;
import com.cur.furniture.database.repository.FurnitureRepository;
import com.cur.furniture.dto.DealCreateDto;
import com.cur.furniture.dto.DealReadDto;
import com.cur.furniture.dto.FurnitureReadDto;

@Mapper(componentModel = "spring")
public abstract class DealMapper {

    @Autowired private FurnitureRepository furnitureRepository;
    @Autowired private FurnitureMapper furnitureMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "furniture", source = "furnitureId", qualifiedByName = "mapFurnitureId")
    public abstract Deal toEntity(DealCreateDto createDto);

    
    @Mapping(target = "content", source = "furniture", qualifiedByName = "mapFurniture")
    public abstract DealReadDto toReadDto(Deal deal);

    @Named("mapFurnitureId")
    protected Furniture mapFurnitureId(Long id) {
        return furnitureRepository.getReferenceById(id);
    }

    @Named("mapFurniture")
    protected FurnitureReadDto mapFurniture(Furniture furniture) {
        return Optional.of(furniture)
            .map(df -> furnitureMapper.toReadDto(furniture))
            .get();
    }

}
