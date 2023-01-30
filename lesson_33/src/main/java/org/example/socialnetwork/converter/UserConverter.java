package org.example.socialnetwork.converter;

import org.example.socialnetwork.dto.UserDto;
import org.example.socialnetwork.model.User;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface UserConverter {
    List<UserDto> toDto(List<User> users);

    UserDto toDto(User createdUser);
}
