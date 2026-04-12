package com.cur.furniture.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cur.furniture.database.entity.Question;
import com.cur.furniture.database.repository.QuestionRepository;
import com.cur.furniture.dto.QuestionCreateDto;
import com.cur.furniture.dto.QuestionReadDto;
import com.cur.furniture.exception.DealNotFoundException;
import com.cur.furniture.mapper.QuestionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;

    @Transactional
    public void create(QuestionCreateDto createDto) {
        Question question = questionMapper.toEntity(createDto);
        questionRepository.save(question);
    }

    public PagedModel<QuestionReadDto> findByPage(Pageable pageable) {
        return new PagedModel<>(questionRepository.findAll(pageable).map(questionMapper::toReadDto));
    }

    public QuestionReadDto findById(Long id) {
        return questionRepository.findById(id)
            .map(questionMapper::toReadDto)
            .orElseThrow(() -> new DealNotFoundException("Question not found: " + id));
    }

}
