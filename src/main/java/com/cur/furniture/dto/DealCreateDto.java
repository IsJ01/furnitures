package com.cur.furniture.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class DealCreateDto {
    @NotBlank String phone;
    @NotNull @Valid private List<DealFurnitureCreateDto> dealFurnitures;

}
