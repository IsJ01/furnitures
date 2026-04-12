package com.cur.furniture.integrational.database.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.entity.Category;
import com.cur.furniture.database.entity.Deal;
import com.cur.furniture.database.entity.DealFurniture;
import com.cur.furniture.database.entity.Furniture;
import com.cur.furniture.database.repository.CategoryRepository;
import com.cur.furniture.database.repository.DealFurnitureRepository;
import com.cur.furniture.database.repository.DealRepository;
import com.cur.furniture.database.repository.FurnitureRepository;
import com.cur.furniture.integrational.IntegrationalTestBase;

public class DealFurnitureRepositoryTest extends IntegrationalTestBase {

    @Autowired private DealRepository dealRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private FurnitureRepository furnitureRepository;
    @Autowired private DealFurnitureRepository dealFurnitureRepository;

    private Category testCategory;
    private Deal testDeal;
    private Furniture testFurniture;

    @BeforeEach
    void setUp() {
        furnitureRepository.deleteAll();
        dealRepository.deleteAll();
        dealFurnitureRepository.deleteAll();
        categoryRepository.deleteAll();
        
        testCategory = new Category(null, "Test category");
        categoryRepository.saveAndFlush(testCategory);

        testDeal = new Deal("79999999999");
        dealRepository.saveAndFlush(testDeal);

        testFurniture = new Furniture(testCategory, 
            "Диван", 3000, 2000, 4000, 
            2000, "???", null
        );
        furnitureRepository.saveAndFlush(testFurniture);
    }
    
    @Test
    void testCreate() {
        DealFurniture df = new DealFurniture(testDeal, testFurniture);

        dealFurnitureRepository.saveAndFlush(df);

        assertThat(df.getDeal()).isEqualTo(testDeal);
        assertThat(df.getFurniture()).isEqualTo(testFurniture);
    }

    @Test
    void testCreateFromDeal() {
        Deal testDeal2 = new Deal("79999999999");

        DealFurniture df = new DealFurniture(testDeal, testFurniture);
        DealFurniture df2 = new DealFurniture(testDeal, testFurniture);
        DealFurniture df3 = new DealFurniture(testDeal, testFurniture);

        testDeal2.setDealFurnitures(List.of(df, df2, df3));
        dealRepository.save(testDeal2);

        assertThat(testDeal2.getDealFurnitures()).hasSize(3)
            .contains(df, df2, df3);
    }

}
