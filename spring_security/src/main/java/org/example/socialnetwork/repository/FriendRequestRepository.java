package org.example.socialnetwork.repository;

import org.example.socialnetwork.model.AppUser;
import org.example.socialnetwork.model.UserRequest;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;


import java.sql.SQLException;
import java.util.List;


public interface FriendRequestRepository extends Repository<UserRequest, Long> {

    @Query("SELECT DISTINCT u.user_id, u.name, u.role, u.created_at " +
            "FROM requests r " +
            "JOIN users u ON r.received_user_id=u.user_id " +
            "WHERE r.request_user_id = :userId AND r.is_approved = true")
    List<AppUser> findUsersApprovedRequest(Long userId);

    @Query("SELECT u.user_id, u.name, u.role, u.created_at " +
            "FROM requests r " +
            "JOIN users u ON r.request_user_id = u.user_id " +
            "WHERE r.request_user_id = :userId AND is_approved = false")
    List<AppUser> findNotApprovedRequestsByUser(Long userId);

    @Modifying
    @Query("INSERT INTO requests(request_user_id, received_user_id, is_approved)" +
            "SELECT :requestedUserId, :receivedUserId, false")
    void createRequest(Long requestedUserId, Long receivedUserId) throws SQLException;


    @Modifying
    @Query("UPDATE requests SET is_approved = true " +
            "WHERE request_user_id = :requestedUserId AND received_user_id = :receivedUserId")
    void approveRequest(Long requestedUserId, Long receivedUserId);

    @Modifying
    @Query("DELETE FROM requests r " +
            "USING users u " +
            "WHERE r.request_user_id = :requestedUserId" +
            " and r.received_user_id = :receivedUserId" +
            " and is_approved=false")
    void deleteRequest(Long requestedUserId, Long receivedUserId);

    @Query("SELECT * FROM requests r WHERE request_user_id = :userId")
    List<UserRequest> getUserRequests(Long userId);
    @Query("SELECT * FROM requests r WHERE r.request_user_id = :userRequestId")
    UserRequest getUserRequest(Long requestId);

    @Modifying
    @Query("UPDATE requests" +
            " SET is_approved = true" +
            " WHERE request_id = :requestId")
    void approveRequest(Long requestId);

    @Modifying
    @Query("UPDATE requests" +
            " SET is_approved = false" +
            " WHERE request_id = :requestId")
    void declineRequest(Long requestId);

    @Query("DELETE FROM requests r " +
            "WHERE r.request_id = :requestId")
    void deleteRequest(Long requestId);
}
