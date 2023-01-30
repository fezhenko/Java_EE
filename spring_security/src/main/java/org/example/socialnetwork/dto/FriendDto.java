package org.example.socialnetwork.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FriendDto {
    @NotNull
    Long friendId;
    @NotNull
    Long requestId;
}
