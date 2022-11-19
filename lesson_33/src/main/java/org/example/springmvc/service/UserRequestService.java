package org.example.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.User;
import org.example.springmvc.repository.FriendRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequestService {
    private final FriendRequest friendRequest;

    public void approveRequest(Long requestedUserId, Long receivedUserId) {
        friendRequest.approveRequest(requestedUserId, receivedUserId);
    }

    public void declineRequest(Long requestedUserId, Long receivedUserId) {
        friendRequest.deleteRequest(requestedUserId, receivedUserId);
    }

    public List<User> findUsersApprovedRequest(Long userId) {
        return friendRequest.findUsersApprovedRequest(userId);
    }

    public List<User> findNotApprovedUserRequests(Long userId) {
        return friendRequest.findNotApprovedRequestsByUser(userId);
    }


    public void saveUserRequest(Long requestedUserId, Long receivedUserId) throws SQLException {
        friendRequest.createRequest(requestedUserId, receivedUserId);
    }

    public boolean isRequestSend(Long requestedUserId, Long receivedUserId) {
        return friendRequest.isRequestApproved(requestedUserId, receivedUserId);
    }
}
