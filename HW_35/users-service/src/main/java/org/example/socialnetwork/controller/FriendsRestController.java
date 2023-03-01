package org.example.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.converter.UserConverter;
import org.example.socialnetwork.dto.FriendDto;
import org.example.socialnetwork.dto.UserDto;
import org.example.socialnetwork.model.AppUser;
import org.example.socialnetwork.service.FriendsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friends")
@RequiredArgsConstructor
public class FriendsRestController {
    private final FriendsService friendsService;
    private final UserConverter userConverter;

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserDto>> getFriends(@PathVariable Long userId) {
        List<AppUser> friendsList = friendsService.findFriends(userId);
        return ResponseEntity.ok(userConverter.toDto(friendsList));
    }

    @GetMapping("/{friendId}/users/{userId} ")
    public ResponseEntity<UserDto> getFriend(@PathVariable Long friendId, @PathVariable Long userId) {
        AppUser friend = friendsService.getFriend(userId, friendId);
        return ResponseEntity
                .ok(userConverter.toDto(friend));
    }

    @PostMapping("/{userId}/decline")
    @ResponseStatus(HttpStatus.OK)
    public void removeFriend(
            @PathVariable Long userId,
            @RequestBody final FriendDto friendDto) {
        friendsService.removeFriend(userId, friendDto.getFriendId());
    }

    @PostMapping("/{userId}/approve")
    @ResponseStatus(HttpStatus.OK)
    public void approveFriend(
            @PathVariable Long userId,
            @RequestBody final FriendDto friendDto) {
        friendsService.addFriend(userId, friendDto.getFriendId());
    }
}
