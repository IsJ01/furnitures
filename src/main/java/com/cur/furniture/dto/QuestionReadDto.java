package com.cur.furniture.dto;

import lombok.Value;

@Value
public class QuestionReadDto {
    Long id;
    String phone;
    String text;
    AnswerReadDto answer;
    boolean isClosed;
}
