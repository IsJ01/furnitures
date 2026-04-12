package com.cur.furniture.dto;

import lombok.Value;

@Value
public class QuestionReadDto {
    String phone;
    String text;
    AnswerReadDto answer;
    boolean isClosed;
}
