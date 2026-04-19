package com.cur.furniture.mapper;

import java.util.Optional;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.entity.Deal;
import com.cur.furniture.database.entity.DealFurniture;
import com.cur.furniture.database.repository.FurnitureRepository;
import com.cur.furniture.dto.DealCreateDto;
import com.cur.furniture.dto.DealFurnitureCreateDto;
import com.cur.furniture.dto.DealReadDto;
import com.cur.furniture.dto.FurnitureReadDto;

@Mapper(componentModel = "spring")
public abstract class DealMapper {

    @Autowired private FurnitureRepository furnitureRepository;
    @Autowired private FurnitureMapper furnitureMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dealFurniture", source = "dealFurniture", qualifiedByName="mapDealFurniture")
    public abstract Deal toEntity(DealCreateDto createDto);

    @Mapping(target = "content", source = "dealFurniture", qualifiedByName="mapFurniture")
    public abstract DealReadDto toReadDto(Deal deal);

    @Named("mapFurniture")
    protected FurnitureReadDto mapFurnitures(DealFurniture dealFurnitures) {
        return Optional.of(dealFurnitures)
            .map(df -> furnitureMapper.toReadDto(df.getFurniture()))
            .get();
    }

    @Named("mapDealFurniture")
    protected DealFurniture mapDealFurniture(DealFurnitureCreateDto dealFurniture) {
        return Optional.of(dealFurniture)
            .map(dto -> dto.getFurnitureId())
            .map(id -> furnitureRepository.getReferenceById(id))
            .map(ft -> new DealFurniture(ft))
            .get();
    }

    @AfterMapping
    protected void links(@MappingTarget Deal deal) {
        deal.getDealFurniture().setDeal(deal);
    }


}
