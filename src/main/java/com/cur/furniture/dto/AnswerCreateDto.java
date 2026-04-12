package com.cur.furniture.dto;

import lombok.Value;

@Value
public class AnswerCreateDto {
    Long questionId;
    Long consultantId;
    String text;
}
