package com.cur.furniture.dto;

import lombok.Value;

@Value
public class DealReadDto {
    Long id;
    String phone;
    FurnitureReadDto content;
}
