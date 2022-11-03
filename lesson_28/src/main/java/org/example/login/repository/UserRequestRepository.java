package org.example.login.repository;

import org.example.login.model.User;
import org.example.login.model.UserRequest;

import java.sql.SQLException;
import java.util.List;

public interface UserRequestRepository {
    List<UserRequest> findIncomingUserRequests();
    List<UserRequest> findOutcomingUserRequests();

    void saveRequest(User user, String userRequestStatus) throws SQLException;

    boolean isRequestSend(String username);

    String getRequestStatus(String username);

}
