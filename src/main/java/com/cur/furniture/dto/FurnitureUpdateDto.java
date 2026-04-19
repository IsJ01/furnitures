package com.cur.furniture.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class FurnitureUpdateDto {
    @NotBlank String name;
    MultipartFile image;
    @NotNull Integer price;
    @NotNull Integer width;
    @NotNull Integer height;
    @NotNull Integer depth;
    @NotBlank String material;
    String description;
}
