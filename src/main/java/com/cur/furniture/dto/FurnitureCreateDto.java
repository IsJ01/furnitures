package com.cur.furniture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class FurnitureCreateDto {
    @NotNull Long categoryId;
    @NotBlank String name;
    @NotNull Integer width;
    @NotNull Integer height;
    @NotNull Integer depth;
    @NotBlank String material;
    String description;
}
