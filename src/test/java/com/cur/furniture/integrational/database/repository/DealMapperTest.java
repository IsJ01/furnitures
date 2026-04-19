package com.cur.furniture.integrational.database.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.entity.Category;
import com.cur.furniture.database.entity.Deal;
import com.cur.furniture.database.entity.Furniture;
import com.cur.furniture.database.repository.CategoryRepository;
import com.cur.furniture.database.repository.DealFurnitureRepository;
import com.cur.furniture.database.repository.DealRepository;
import com.cur.furniture.database.repository.FurnitureRepository;
import com.cur.furniture.dto.DealCreateDto;
import com.cur.furniture.dto.DealFurnitureCreateDto;
import com.cur.furniture.integrational.IntegrationalTestBase;
import com.cur.furniture.mapper.DealMapper;

public class DealMapperTest extends IntegrationalTestBase {

    @Autowired private DealMapper dealMapper;
    @Autowired private DealRepository dealRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private FurnitureRepository furnitureRepository;
    @Autowired private DealFurnitureRepository dealFurnitureRepository;

    private Category testCategory;
    private Furniture testFurniture;

    @BeforeEach
    void setUp() {
        furnitureRepository.deleteAll();
        dealRepository.deleteAll();
        dealFurnitureRepository.deleteAll();
        categoryRepository.deleteAll();
        
        testCategory = new Category(null, "Test category");
        categoryRepository.saveAndFlush(testCategory);

        testFurniture = new Furniture(testCategory, 
            "Диван", null, 3000, 2000, 4000, 
            2000, "???", null
        );
        furnitureRepository.saveAndFlush(testFurniture);
    }

    @Test
    void testCreateFromDeal() {
        DealCreateDto createDto = new DealCreateDto("79999999999", 
            new DealFurnitureCreateDto(testFurniture.getId())
        );

        Deal deal = dealMapper.toEntity(createDto);

        dealRepository.saveAndFlush(deal);

    }
}
