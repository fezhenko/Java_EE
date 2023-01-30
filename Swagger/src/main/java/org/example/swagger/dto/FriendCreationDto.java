package org.example.swagger.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
@Data
@Jacksonized
@Builder
public class FriendCreationDto {
    @NotNull
    Long friendId;
    @NotNull
    Long requestId;
}
