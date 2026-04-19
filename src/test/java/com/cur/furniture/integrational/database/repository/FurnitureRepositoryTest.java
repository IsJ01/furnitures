package com.cur.furniture.integrational.database.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.cur.furniture.database.entity.Category;
import com.cur.furniture.database.entity.Furniture;
import com.cur.furniture.database.repository.CategoryRepository;
import com.cur.furniture.database.repository.FurnitureRepository;
import com.cur.furniture.integrational.IntegrationalTestBase;

public class FurnitureRepositoryTest extends IntegrationalTestBase {

    @Autowired private CategoryRepository categoryRepository;
    @Autowired private FurnitureRepository furnitureRepository;

    private Category testCategory;

    @BeforeEach
    void setUp() {
        furnitureRepository.deleteAll();
        categoryRepository.deleteAll();
        testCategory = new Category(null, "Test category");
        categoryRepository.saveAndFlush(testCategory);
    }

    @Test
    void testCreate() {
        Furniture furniture = new Furniture(testCategory, 
            "Диван", null, 3000, 2000, 4000, 
            2000, "???", null
        );

        furnitureRepository.saveAndFlush(furniture);

        Furniture retrievedFurniture = furnitureRepository.findById(furniture.getId()).orElseThrow();

        
        assertThat(retrievedFurniture.getCategory()).isEqualTo(testCategory);
        assertThat(retrievedFurniture.getName()).isEqualTo("Диван");
        assertThat(retrievedFurniture.getWidth()).isEqualTo(2000);
        assertThat(retrievedFurniture.getHeight()).isEqualTo(4000);
        assertThat(retrievedFurniture.getDepth()).isEqualTo(2000);
        assertThat(retrievedFurniture.getMaterial()).isEqualTo("???");
        assertThat(retrievedFurniture.getDescription()).isNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void testCreateWithInvalidFields(String field) {
        Furniture furniture = new Furniture(testCategory, 
            field, null, 3000, 2000, 4000, 
            2000, field, null
        );

        assertThrows(
            DataIntegrityViolationException.class, 
            () -> furnitureRepository.saveAndFlush(furniture)
        );
    }

}
