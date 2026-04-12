package com.cur.furniture.mapper;

import java.util.ArrayList;
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

@Mapper(componentModel = "spring")
public abstract class DealMapper {

    @Autowired private FurnitureRepository furnitureRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dealFurnitures", source = "dealFurnitures", qualifiedByName="mapDealFurnitures")
    public abstract Deal toEntity(DealCreateDto createDto);

    @Named("mapDealFurnitures")
    protected List<DealFurniture> mapDealFurnitures(List<DealFurnitureCreateDto> dealFurnitures) {
        List<DealFurniture> enities = new ArrayList<>();
        for (DealFurnitureCreateDto createDto: dealFurnitures) {
            DealFurniture entity = new DealFurniture(
                furnitureRepository.getReferenceById(createDto.getFurnitureId())
            );
            enities.add(entity);
        }
        return enities;
    }

    @AfterMapping
    protected void links(@MappingTarget Deal deal) {
        for (DealFurniture df: deal.getDealFurnitures()) {
            df.setDeal(deal);
        }
    }


}
