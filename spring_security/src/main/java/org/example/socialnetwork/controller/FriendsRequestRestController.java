package org.example.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.converter.UserRequestConverter;
import org.example.socialnetwork.dto.ApproveDeclineFriendRequestDto;
import org.example.socialnetwork.dto.FriendRequestsDto;
import org.example.socialnetwork.model.UserRequest;
import org.example.socialnetwork.service.FriendsService;
import org.example.socialnetwork.service.UserRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendRequests")
@RequiredArgsConstructor
public class FriendsRequestRestController {

    private final UserRequestService userRequestService;
    private final UserRequestConverter userRequestConverter;
    private final FriendsService friendsService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<FriendRequestsDto>> getFriendRequests(
            @PathVariable Long userId
    ) {
        List<UserRequest> userRequests = userRequestService.getUserRequests(userId);
        return ResponseEntity.ok(userRequestConverter.toDto(userRequests));
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<FriendRequestsDto> getFriendRequestById(
            @PathVariable Long requestId
    ) {
        UserRequest userRequest = userRequestService.getUserRequest(requestId);
        return ResponseEntity.ok(userRequestConverter.toDto(userRequest));
    }

    @PostMapping("/{requestId}/approve")
    public ResponseEntity<String> approveFriendRequest(
            @PathVariable Long requestId,
            @RequestBody ApproveDeclineFriendRequestDto requestDto
    ) {
        userRequestService.approveRequest(requestId);
        friendsService.addFriend(requestDto.getReceivedUserId(), requestId);
        return ResponseEntity.ok("user with id %d successfully approve user with id %d".formatted(requestDto.getRequestedUserId(), requestDto.getReceivedUserId()));
    }

    @PostMapping("/{requestId}/decline")
    public ResponseEntity<String> declineFriendRequest(
            @PathVariable Long requestId) {
        userRequestService.declineRequest(requestId);
        userRequestService.deleteRequest(requestId);
        return ResponseEntity.ok("Request with id %d has been declined and deleted".formatted(requestId));
    }

}
