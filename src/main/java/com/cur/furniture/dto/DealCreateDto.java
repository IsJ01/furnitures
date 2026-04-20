package com.cur.furniture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class DealCreateDto {
    @NotBlank String phone;
    @NotNull Long furnitureId;

}
