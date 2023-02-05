package org.example.socialnetwork.converter;

import org.example.socialnetwork.dto.FriendRequestsDto;
import org.example.socialnetwork.model.UserRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserRequestConverter {
    List<FriendRequestsDto> toDto(List<UserRequest> userRequests);

    FriendRequestsDto toDto(UserRequest userRequest);
}
