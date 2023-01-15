package org.example.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.model.AppUser;
import org.example.socialnetwork.repository.FriendsRepository;
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

    public void removeFriend(Long friendId) {
        friendsRepository.removeFriend(friendId);
    }

    public AppUser getFriend(Long friendId) {
        return friendsRepository.getFriend(friendId);
    }
    public AppUser getFriend(Long userId, Long friendId) {
        return friendsRepository.getFriend(userId, friendId);
    }
}
