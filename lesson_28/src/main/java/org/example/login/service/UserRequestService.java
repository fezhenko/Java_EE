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

    public List<UserRequest> findUserRequests() {
        return userRequestRepository.findUserRequests();
    }

    public void saveUserRequest(User user, String userRequestStatus) throws SQLException {
        userRequestRepository.saveRequest(user, userRequestStatus);
    }

    public String getUserRequestStatus(String username) {
        return userRequestRepository.getRequestStatus(username);
    }

    public boolean isUserRequestSend(String username) {
        return userRequestRepository.isRequestSend(username);
    }
}
