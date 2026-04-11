package com.cur.furniture.dto;

import lombok.Value;

@Value
public class FurnitureReadDto {
    Long id;
    Long categoryId;
    String name;
    Integer width;
    Integer height;
    Integer depth;
    String material;
    String description;
}
