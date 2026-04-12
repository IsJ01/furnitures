package com.cur.furniture.integrational.database.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.entity.Answer;
import com.cur.furniture.database.entity.Question;
import com.cur.furniture.database.entity.User;
import com.cur.furniture.database.entity.User.Role;
import com.cur.furniture.database.repository.AnswerRepository;
import com.cur.furniture.database.repository.QuestionRepository;
import com.cur.furniture.database.repository.UserRepository;
import com.cur.furniture.integrational.IntegrationalTestBase;

public class AnswerRepositroyTest extends IntegrationalTestBase {

    @Autowired private QuestionRepository questionRepository; 
    @Autowired private UserRepository userRepository; 
    @Autowired private AnswerRepository answerRepository; 

    private Question testQuestion;
    private User testUser;

    @BeforeEach
    void setUp() {
        questionRepository.deleteAll();
        userRepository.deleteAll();
        answerRepository.deleteAll();

        testQuestion = new Question("79999999999", "?");
        questionRepository.saveAndFlush(testQuestion);
        testUser = new User("Test user", "pass", Role.CONSULTANT);
        userRepository.saveAndFlush(testUser);
    }

    @Test
    void testCreate() {
        Answer answer = new Answer(testQuestion, testUser, "!");

        answerRepository.saveAndFlush(answer);

        Question retrievedQuestion = questionRepository.findById(testQuestion.getId()).orElseThrow();
        
        assertThat(retrievedQuestion.getAnswer()).isEqualTo(answer);
        assertThat(retrievedQuestion.isClosed()).isTrue();
        assertThat(retrievedQuestion.getAnswer().getConsultant()).isEqualTo(testUser);
        assertThat(retrievedQuestion.getAnswer().getText()).isEqualTo("!");

    }

}
