package com.cur.furniture.dto;

import java.util.List;

import lombok.Value;

@Value
public class CategoryReadDto {
    Long id;
    String name;
    List<CategoryReadDto> children;
}
