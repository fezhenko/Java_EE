package org.example.swagger.service;

import lombok.RequiredArgsConstructor;
import org.example.swagger.model.AppUser;
import org.example.swagger.repository.FriendsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendsService {
    private final FriendsRepository friendsRepository;

    public List<AppUser> findFriends(Long userId) {
        return friendsRepository.findFriends(userId);
    }
    public void addFriend(Long friendId, Long requestId) {
        friendsRepository.addFriend(friendId, requestId);
    }
    public AppUser getFriend(Long userId, Long friendId) {
        return friendsRepository.getFriend(userId, friendId);
    }

    public void removeFriend(Long userId, Long friendId) {
        friendsRepository.removeFriend(userId, friendId);
    }
}
