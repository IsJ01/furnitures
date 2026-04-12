package com.cur.furniture.mapper;

import java.util.List;

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
    @Mapping(target = "dealFurnitures", source = "dealFurnitures", qualifiedByName="mapDealFurnitures")
    public abstract Deal toEntity(DealCreateDto createDto);

    @Mapping(target = "content", source = "dealFurnitures", qualifiedByName="mapFurnitures")
    public abstract DealReadDto toReadDto(Deal deal);

    @Named("mapFurnitures")
    protected List<FurnitureReadDto> mapFurnitures(List<DealFurniture> dealFurnitures) {
        return dealFurnitures.stream()
            .map(df -> furnitureMapper.toReadDto(df.getFurniture()))
            .toList();
    }

    @Named("mapDealFurnitures")
    protected List<DealFurniture> mapDealFurnitures(List<DealFurnitureCreateDto> dealFurnitures) {
        return dealFurnitures.stream()
            .map(dto -> dto.getFurnitureId())
            .map(id -> furnitureRepository.getReferenceById(id))
            .map(ft -> new DealFurniture(ft))
            .toList();
    }

    @AfterMapping
    protected void links(@MappingTarget Deal deal) {
        for (DealFurniture df: deal.getDealFurnitures()) {
            df.setDeal(deal);
        }
    }


}
