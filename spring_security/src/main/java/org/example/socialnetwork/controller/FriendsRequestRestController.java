package org.example.socialnetwork.controller;

import org.example.socialnetwork.converter.UserRequestConverter;
import org.example.socialnetwork.dto.ApproveFriendRequestDto;
import org.example.socialnetwork.dto.CreateFriendRequestDto;
import org.example.socialnetwork.dto.FriendRequestsDto;
import org.example.socialnetwork.model.UserRequest;
import org.example.socialnetwork.service.FriendsService;
import org.example.socialnetwork.service.UserRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/friendRequests")
public class FriendsRequestRestController {

    private final UserRequestService userRequestService;
    private final UserRequestConverter userRequestConverter;
    private final FriendsService friendsService;

    public FriendsRequestRestController(
            UserRequestService userRequestService,
            UserRequestConverter userRequestConverter,
            FriendsService friendsService) {
        this.userRequestService = userRequestService;
        this.userRequestConverter = userRequestConverter;
        this.friendsService = friendsService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FriendRequestsDto>> getFriendRequests(
           @PathVariable Long userId
    ) {
        List<UserRequest> userRequests = userRequestService.getUserRequests(userId);
        return ResponseEntity.ok(userRequestConverter.toDto(userRequests));
    }

    @GetMapping("/{userId}/{requestId}")
    public ResponseEntity<FriendRequestsDto> getFriendRequestById(
            @PathVariable Long userId,
            @PathVariable Long requestId
    ) {
        UserRequest userRequest = userRequestService.getUserRequest(userId, requestId);
        return ResponseEntity.ok(userRequestConverter.toDto(userRequest));
    }

    @PostMapping("/create")
    public ResponseEntity<FriendRequestsDto> createRequest(
            @RequestBody CreateFriendRequestDto createFriendRequestDto
    ) {
        try {
            userRequestService.createUserRequest(createFriendRequestDto.getRequestedUserId(),
                    createFriendRequestDto.getReceivedUserId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UserRequest userRequest =
                userRequestService.getUserRequestByUsersIds(createFriendRequestDto.getRequestedUserId(),
                        createFriendRequestDto.getReceivedUserId());
        return ResponseEntity.ok(userRequestConverter.toDto(userRequest));
    }

    @PostMapping("/{requestId}/approve")
    public ResponseEntity<String> approveFriendRequest(
            @PathVariable Long requestId,
            @RequestBody ApproveFriendRequestDto requestDto
    ) {
        userRequestService.approveRequest(requestId);
        friendsService.addFriend(requestDto.getReceivedUserId(), requestId);
        return ResponseEntity
                .ok("user with id %d successfully approve user with id %d"
                        .formatted(requestDto.getRequestedUserId(), requestDto.getReceivedUserId()));
    }

    @PostMapping("/{requestId}/decline")
    public ResponseEntity<String> declineFriendRequest(
            @PathVariable Long requestId) {
        userRequestService.declineRequest(requestId);
        userRequestService.deleteRequest(requestId);
        return ResponseEntity
                .ok("Request with id %d has been declined and deleted"
                        .formatted(requestId));
    }

}
