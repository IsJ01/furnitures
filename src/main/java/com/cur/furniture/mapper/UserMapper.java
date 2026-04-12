package com.cur.furniture.mapper;

import org.mapstruct.Mapper;

import com.cur.furniture.database.entity.User;
import com.cur.furniture.dto.UserReadDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserReadDto toReadDto(User user);

}
