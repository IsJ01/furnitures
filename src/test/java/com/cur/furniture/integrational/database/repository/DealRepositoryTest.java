package com.cur.furniture.integrational.database.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.entity.Category;
import com.cur.furniture.database.entity.Deal;
import com.cur.furniture.database.entity.Furniture;
import com.cur.furniture.database.repository.CategoryRepository;
import com.cur.furniture.database.repository.DealRepository;
import com.cur.furniture.database.repository.FurnitureRepository;
import com.cur.furniture.integrational.IntegrationalTestBase;

public class DealRepositoryTest extends IntegrationalTestBase {

    @Autowired private DealRepository dealRepository;
    
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private FurnitureRepository furnitureRepository;

    private Category testCategory;
    private Furniture testFurniture;

    @BeforeEach
    void setUp() {
        dealRepository.deleteAll();
        furnitureRepository.deleteAll();
        dealRepository.deleteAll();
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
    void testCreate() {
        Deal deal = new Deal("79999999999", testFurniture);

        dealRepository.saveAndFlush(deal);

        assertThat(deal.getPhone()).isEqualTo("79999999999");
    }

}
