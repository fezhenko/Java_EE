package org.example.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.converter.UserConverter;
import org.example.socialnetwork.dto.UserDto;
import org.example.socialnetwork.model.AppUser;
import org.example.socialnetwork.service.FriendsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/{userId}/{friendId}")
    public ResponseEntity<UserDto> getFriend(@PathVariable Long userId, Long friendId) {
        AppUser friend = friendsService.getFriend(userId, friendId);
        return ResponseEntity
                .ok(userConverter.toDto(friend));
    }

//
//    @PostMapping("/{userId}/add/{friendId}")
//    @PostMapping("/{userId}/approve/{friendId}")
//    @PostMapping("/{userId}/decline/{friendId}")
}
