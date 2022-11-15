package org.example.login.service;

import org.example.login.model.User;
import org.example.login.repository.UserRequestRepository;

import java.sql.SQLException;
import java.util.List;

public class UserRequestService {
    private final UserRequestRepository userRequestRepository;

    public UserRequestService(UserRequestRepository userRequestRepository) {
        this.userRequestRepository = userRequestRepository;
    }

    public void approveRequest(Long requestedUserId, Long receivedUserId) {
        userRequestRepository.approveRequest(requestedUserId, receivedUserId);
    }

    public void declineRequest(Long requestedUserId, Long receivedUserId) {
        userRequestRepository.deleteRequest(requestedUserId, receivedUserId);
    }

    public List<User> findUsersApprovedRequest(Long userId) {
        return userRequestRepository.findUsersApprovedRequest(userId);
    }

    public List<User> findNotApprovedUserRequests(Long userId) {
        return userRequestRepository.findNotApprovedRequestsByUser(userId);
    }

    public List<User> findUsersWithNotApprovedRequest(Long userId) {
        return userRequestRepository.findUsersWithNotApprovedRequest(userId);
    }

    public void saveUserRequest(Long requestedUserId, Long receivedUserId) throws SQLException {
        userRequestRepository.createRequest(requestedUserId, receivedUserId);
    }

    public boolean isRequestSend(Long requestedUserId, Long receivedUserId) {
        return userRequestRepository.isRequestApproved(requestedUserId, receivedUserId);
    }
}
