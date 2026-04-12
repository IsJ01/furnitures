package com.cur.furniture.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DealFurnitureCreateDto {
   @NotNull Long furnitureId;
}
