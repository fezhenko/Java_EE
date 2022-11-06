package org.example.login.repository;

import org.example.login.model.User;
import org.example.login.model.UserRequest;

import java.sql.SQLException;
import java.util.List;

public interface UserRequestRepository {
    List<UserRequest> findApprovedUserRequests();
    List<UserRequest> findNotApprovedUserRequests();

    List<User> findUsersWithNotApprovedRequest(String username);

    void saveRequest(String requestedUsername, String receivedUsername) throws SQLException;

    boolean isRequestApproved(String requestedUsername, String receivedUsername);

    void approveRequest(String requestedUsername, String receivedUsername);

    void deleteRequest(Long requestId);
}
