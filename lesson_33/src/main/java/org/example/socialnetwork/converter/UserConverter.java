package org.example.socialnetwork.converter;

import org.example.socialnetwork.dto.UserDto;
import org.example.socialnetwork.model.User;

import java.util.List;

public interface UserConverter {
    List<UserDto> toDto(List<User> users);

    UserDto toDto(User createdUser);
}
