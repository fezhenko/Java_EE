package org.example.socialnetwork.repository;

import org.example.socialnetwork.model.AppUser;

import java.sql.SQLException;
import java.util.List;


public interface FriendRequest {
    List<AppUser> findUsersApprovedRequest(Long userId);
    List<AppUser> findNotApprovedRequestsByUser(Long userId);

    void createRequest(Long requestedUserId, Long receivedUserId) throws SQLException;

    boolean isRequestApproved(Long requestedUserId, Long receivedUserId);

    void approveRequest(Long requestedUserId, Long receivedUserId);

    void deleteRequest(Long requestedUserId, Long receivedUserId);
}
