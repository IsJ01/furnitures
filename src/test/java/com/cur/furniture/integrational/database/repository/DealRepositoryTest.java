package com.cur.furniture.integrational.database.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.entity.Deal;
import com.cur.furniture.database.repository.DealRepository;

import com.cur.furniture.integrational.IntegrationalTestBase;

public class DealRepositoryTest extends IntegrationalTestBase {

    @Autowired private DealRepository dealRepository;

    @BeforeEach
    void setUp() {
        dealRepository.deleteAll();
    }
    
    @Test
    void testCreate() {
        Deal deal = new Deal("79999999999");

        dealRepository.saveAndFlush(deal);

        assertThat(deal.getPhone()).isEqualTo("79999999999");
    }

}
