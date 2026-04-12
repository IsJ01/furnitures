package com.cur.furniture.dto;

import com.cur.furniture.database.entity.User.Role;

import lombok.Value;

@Value
public class UserReadDto {
    String username;
    Role role;
}
