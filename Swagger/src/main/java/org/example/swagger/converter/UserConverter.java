package org.example.swagger.converter;

import org.example.swagger.dto.UserDto;
import org.example.swagger.model.AppUser;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface UserConverter {
    List<UserDto> toDto(List<AppUser> appUsers);

    UserDto toDto(AppUser createdAppUser);
}
