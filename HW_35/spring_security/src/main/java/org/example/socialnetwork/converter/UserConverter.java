package org.example.socialnetwork.converter;

import org.example.socialnetwork.dto.AppUserDto;
import org.example.socialnetwork.dto.UserDto;
import org.example.socialnetwork.model.AppUser;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface UserConverter {
    List<UserDto> toDto(List<AppUser> appUsers);

    UserDto toDto(AppUser createdAppUser);

    AppUserDto toAppUserDto(AppUser appUser);
}
