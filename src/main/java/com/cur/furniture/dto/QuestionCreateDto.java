package com.cur.furniture.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class QuestionCreateDto {
    @NotBlank String phone;
    @NotBlank String text;
}
