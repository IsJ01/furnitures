package com.cur.furniture.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class DealCreateDto {
    @NotBlank String phone;
    @NotNull @Valid private DealFurnitureCreateDto dealFurniture;

}
