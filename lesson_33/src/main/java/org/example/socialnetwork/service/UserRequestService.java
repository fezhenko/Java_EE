package org.example.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.model.User;
import org.example.socialnetwork.repository.FriendRequestRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequestService {
    private final FriendRequestRepository friendRequestRepository;

    public void approveRequest(Long requestedUserId, Long receivedUserId) {
        friendRequestRepository.approveRequest(requestedUserId, receivedUserId);
    }

    public void declineRequest(Long requestedUserId, Long receivedUserId) {
        friendRequestRepository.deleteRequest(requestedUserId, receivedUserId);
    }

    public List<User> findUsersApprovedRequest(Long userId) {
        return friendRequestRepository.findUsersApprovedRequest(userId);
    }

    public List<User> findNotApprovedUserRequests(Long userId) {
        return friendRequestRepository.findNotApprovedRequestsByUser(userId);
    }


    public void saveUserRequest(Long requestedUserId, Long receivedUserId) throws SQLException {
        friendRequestRepository.createRequest(requestedUserId, receivedUserId);
    }

    public boolean isRequestSend(Long requestedUserId, Long receivedUserId) {
        return friendRequestRepository.isRequestApproved(requestedUserId, receivedUserId);
    }
}
