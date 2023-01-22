package org.example.socialnetwork.repository;

import org.example.socialnetwork.model.User;

import java.sql.SQLException;
import java.util.List;

public interface FriendRequest {
    List<User> findUsersApprovedRequest(Long userId);
    List<User> findNotApprovedRequestsByUser(Long userId);

    void createRequest(Long requestedUserId, Long receivedUserId) throws SQLException;

    boolean isRequestApproved(Long requestedUserId, Long receivedUserId);

    void approveRequest(Long requestedUserId, Long receivedUserId);

    void deleteRequest(Long requestedUserId, Long receivedUserId);
}