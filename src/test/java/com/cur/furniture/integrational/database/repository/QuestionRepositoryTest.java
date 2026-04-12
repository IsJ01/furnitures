package com.cur.furniture.integrational.database.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.entity.Question;
import com.cur.furniture.database.repository.QuestionRepository;
import com.cur.furniture.integrational.IntegrationalTestBase;

public class QuestionRepositoryTest extends IntegrationalTestBase {

    @Autowired private QuestionRepository questionRepository; 

    @BeforeEach
    void setUp() {
        questionRepository.deleteAll();
    }

    @Test
    void testCreate() {
        Question question = new Question("79999999999", "?");
        questionRepository.saveAndFlush(question);
        assertThat(question.getPhone()).isEqualTo("79999999999");
        assertThat(question.getText()).isEqualTo("?");
        assertThat(question.isClosed()).isFalse();
    }

}
