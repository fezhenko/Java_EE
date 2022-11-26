package org.example.springmvc.dto;

import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class FriendRequestsDto {
    @NotEmpty
    Long userId;
}
