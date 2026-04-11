package com.cur.furniture.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class SignInDto {
    @NotBlank String username;
    @NotBlank String password;
}
