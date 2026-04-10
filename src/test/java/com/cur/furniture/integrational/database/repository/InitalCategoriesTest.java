package com.cur.furniture.integrational.database.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.repository.CategoryRepository;
import com.cur.furniture.integrational.IntegrationalTestBase;

public class InitalCategoriesTest extends IntegrationalTestBase {

    @Autowired private CategoryRepository categoryRepository;

    @Test
    void testGetParentCategories() {
        var res = categoryRepository.findAll().stream()
            .filter(c -> c.getParent() == null)
            .toList();
        assertThat(res).hasSize(8);
    }

    @Test
    void testGetChildCategories() {
        var res = categoryRepository.findAll().stream()
            .filter(c -> c.getParent() != null)
            .toList();
        assertThat(res).hasSize(17);
    }

}
