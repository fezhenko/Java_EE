package org.example.swagger.converter;

import org.example.swagger.dto.FriendRequestsDto;
import org.example.swagger.model.UserRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserRequestConverter {
    List<FriendRequestsDto> toDto(List<UserRequest> userRequests);

    FriendRequestsDto toDto(UserRequest userRequest);
}
