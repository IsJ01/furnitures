package com.cur.furniture.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cur.furniture.database.entity.Answer;
import com.cur.furniture.database.repository.AnswerRepository;
import com.cur.furniture.dto.AnswerCreateDto;
import com.cur.furniture.mapper.AnswerMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerMapper answerMapper;
    private final AnswerRepository answerRepository;

    @Transactional
    public void create(AnswerCreateDto createDto) {
        Answer question = answerMapper.toEntity(createDto);
        answerRepository.save(question);
    }

}
