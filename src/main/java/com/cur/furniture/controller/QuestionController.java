package com.cur.furniture.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cur.furniture.database.entity.User;
import com.cur.furniture.dto.AnswerCreateDto;
import com.cur.furniture.dto.QuestionCreateDto;
import com.cur.furniture.dto.QuestionReadDto;
import com.cur.furniture.service.AnswerService;
import com.cur.furniture.service.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @GetMapping
    public PagedModel<QuestionReadDto> findByPage(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return questionService.findByPage(pageable);
    }

    @GetMapping("/{id}")
    public QuestionReadDto findById(@PathVariable Long id) {
        return questionService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid QuestionCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.create(createDto));
    }

    @PostMapping("/answers")
    public ResponseEntity<?> createAnswer(@RequestBody @Valid AnswerCreateDto createDto, @AuthenticationPrincipal User user) {
        answerService.create(user, createDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
