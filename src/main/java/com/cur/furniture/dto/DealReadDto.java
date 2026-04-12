package com.cur.furniture.dto;

import java.util.List;

import lombok.Value;

@Value
public class DealReadDto {
    Long id;
    String phone;
    List<FurnitureReadDto> content;
}
