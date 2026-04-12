package com.cur.furniture.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class SignUpDto {
    @NotBlank String username;
    @NotBlank String password;
}
