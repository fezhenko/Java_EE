package org.example.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.model.UserRequest;
import org.example.socialnetwork.repository.FriendRequestRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequestService {
    private final FriendRequestRepository friendRequestRepository;

    public List<UserRequest> getUserRequests(Long userId) {
        return friendRequestRepository.getUserRequests(userId);
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

    public UserRequest getUserRequest(Long userId, Long requestId) {
        return friendRequestRepository.getUserRequest(userId, requestId);
    }

    public void createUserRequest(Long requestedUserId, Long receivedUserId) throws SQLException {
        friendRequestRepository.createRequest(requestedUserId, receivedUserId);
    }

    public UserRequest getUserRequestByUsersIds(Long requestedUserId, Long receivedUserId) {
        return friendRequestRepository.getUserRequestByUsersIds(requestedUserId, receivedUserId);
    }
}
