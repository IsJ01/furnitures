package com.cur.furniture.dto;

import org.openapitools.jackson.nullable.JsonNullable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FurniturePatchDto {
    JsonNullable<String> name = JsonNullable.undefined();
    JsonNullable<Integer> price = JsonNullable.undefined();
    JsonNullable<Integer> width = JsonNullable.undefined();
    JsonNullable<Integer> height = JsonNullable.undefined();
    JsonNullable<Integer> depth = JsonNullable.undefined();
    JsonNullable<String> material = JsonNullable.undefined();
    JsonNullable<String> description = JsonNullable.undefined();
}
