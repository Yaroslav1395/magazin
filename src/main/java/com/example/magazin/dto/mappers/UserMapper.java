package com.example.magazin.dto.mappers;

import com.example.magazin.dto.user.UserDto;
import com.example.magazin.entity.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);
    UserDto toDto(User user);
}
