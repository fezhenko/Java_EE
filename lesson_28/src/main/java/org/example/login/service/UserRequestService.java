package org.example.login.service;

import org.example.login.model.User;
import org.example.login.model.UserRequest;
import org.example.login.repository.UserRequestRepository;

import java.sql.SQLException;
import java.util.List;

public class UserRequestService {
    private final UserRequestRepository userRequestRepository;

    public UserRequestService(UserRequestRepository userRequestRepository) {
        this.userRequestRepository = userRequestRepository;
    }

    public void approveRequest(String requestedUsername, String receivedUsername) {
        userRequestRepository.approveRequest(requestedUsername, receivedUsername);
    }

    public List<UserRequest> findApprovedUserRequests() {
        return userRequestRepository.findApprovedUserRequests();
    }

    public List<UserRequest> findNotApprovedUserRequests() {
        return userRequestRepository.findNotApprovedUserRequests();
    }

    public List<User> findUsersWithNotApprovedRequest(String username) {
        return userRequestRepository.findUsersWithNotApprovedRequest(username);
    }

    public void saveUserRequest(String requestedUsername, String receivedUsername) throws SQLException {
        userRequestRepository.saveRequest(requestedUsername, receivedUsername);
    }

    public boolean isRequestSend(String requestedUsername, String receivedUsername) {
        return userRequestRepository.isRequestApproved(requestedUsername, receivedUsername);
    }
}
