package org.example.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.converter.UserConverter;
import org.example.socialnetwork.dto.UserDto;
import org.example.socialnetwork.model.AppUser;
import org.example.socialnetwork.service.FriendsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friends")
@RequiredArgsConstructor
public class FriendsRestController {
    FriendsService friendsService;
    UserConverter userConverter;

    @GetMapping
    public ResponseEntity<List<UserDto>> getFriends() {
        List<AppUser> friendsList = friendsService.findFriends();
        return ResponseEntity.ok(userConverter.toDto(friendsList));
    }

    @GetMapping("/{friendId}")
    public ResponseEntity<UserDto> getFriend(@PathVariable Long friendId) {
        AppUser friend = friendsService.getFriend(friendId);
        return ResponseEntity
                .ok(userConverter.toDto(friend));
    }
}
