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
        Category category = new Category("Диваны");
        categoryRepository.saveAndFlush(category);
        Category retrievedCategory = categoryRepository.findById(category.getId()).orElseThrow();
        assertThat(retrievedCategory.getName()).isEqualTo("Диваны");
    }

    @Test
    void testRepeatedCreate() {
        categoryRepository.saveAndFlush(new Category("Диваны"));
        assertThrows(
            DataIntegrityViolationException.class, 
            () -> categoryRepository.saveAndFlush(new Category("Диваны"))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void testCreateWithInvalidName(String name) {
        assertThrows(
            DataIntegrityViolationException.class, 
            () -> categoryRepository.saveAndFlush(new Category(name))
        );
    }

}
