package com.cur.furniture.dto;

import lombok.Value;

@Value
public class FurnitureFilterDto {
    Long categoryId;
    String name;
    Integer width;
    Integer height;
    Integer depth;
    String material;
}
