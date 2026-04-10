package com.cur.furniture.integrational.database.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.cur.furniture.database.entity.Category;
import com.cur.furniture.database.repository.CategoryRepository;
import com.cur.furniture.integrational.IntegrationalTestBase;

public class CategoriesRepositoryTest extends IntegrationalTestBase {

    @Autowired private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
    }

    @Test
    void testCreate() {
        Category category = new Category(null, "Диваны");
        categoryRepository.saveAndFlush(category);
        Category retrievedCategory = categoryRepository.findById(category.getId()).orElseThrow();
        assertThat(retrievedCategory.getParent()).isNull();
        assertThat(retrievedCategory.getName()).isEqualTo("Диваны");
    }

    @Test
    void testRepeatedCreate() {
        categoryRepository.saveAndFlush(new Category(null, "Диваны"));
        assertThrows(
            DataIntegrityViolationException.class, 
            () -> categoryRepository.saveAndFlush(new Category(null, "Диваны"))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void testCreateWithInvalidName(String name) {
        assertThrows(
            DataIntegrityViolationException.class, 
            () -> categoryRepository.saveAndFlush(new Category(null, name))
        );
    }

    @Test
    void testCreateWithParent() {
        Category category = new Category(null, "Корпусная мебель");
        categoryRepository.saveAndFlush(category);

        Category category2 = new Category(category, "Диваны");
        categoryRepository.saveAndFlush(category2);

        Category retrievedCategory = categoryRepository.findById(category2.getId()).orElseThrow();
        assertThat(retrievedCategory.getParent()).isEqualTo(category);
        assertThat(retrievedCategory.getName()).isEqualTo("Диваны");
    }

}
