package org.example.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.model.UserRequest;
import org.example.socialnetwork.repository.FriendRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequestService {
    private final FriendRequestRepository friendRequestRepository;

    public List<UserRequest> getUserRequests(Long userId) {
        return friendRequestRepository.getUserRequests(userId);
    }

    public UserRequest getUserRequest(Long requestId) {
        return friendRequestRepository.getUserRequest(requestId);
    }

    public void approveRequest(Long requestId) {
        friendRequestRepository.approveRequest(requestId);
    }

    public void declineRequest(Long requestId) {
        friendRequestRepository.declineRequest(requestId);
    }

    public void deleteRequest(Long requestId) {
        friendRequestRepository.deleteRequest(requestId);
    }
}
