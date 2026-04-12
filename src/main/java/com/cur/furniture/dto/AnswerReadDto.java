package com.cur.furniture.dto;

import lombok.Value;

@Value
public class AnswerReadDto {
    UserReadDto consultant;
    String text;
}
