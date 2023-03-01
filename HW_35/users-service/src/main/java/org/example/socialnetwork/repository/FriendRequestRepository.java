package org.example.socialnetwork.repository;

import org.example.socialnetwork.model.AppUser;
import org.example.socialnetwork.model.UserRequest;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


import java.sql.SQLException;
import java.util.List;


public interface FriendRequestRepository extends Repository<UserRequest, Long> {

    @Query("SELECT DISTINCT u.user_id, u.name, u.role, u.created_at " +
            "FROM requests r " +
            "JOIN users u ON r.received_user_id=u.user_id " +
            "WHERE r.request_user_id = :userId AND r.is_approved = true")
    List<AppUser> findUsersApprovedRequest(
            @Param("userId") Long userId);

    @Query("SELECT u.user_id, u.name, u.role, u.created_at " +
            "FROM requests r " +
            "JOIN users u ON r.request_user_id = u.user_id " +
            "WHERE r.request_user_id = :userId AND is_approved = false")
    List<AppUser> findNotApprovedRequestsByUser(
            @Param("userId") Long userId);

    @Modifying
    @Query("INSERT INTO requests(request_id, request_user_id, received_user_id, is_approved, created_at)" +
            "VALUES (default, :requestedUserId, :receivedUserId, false, now())")
    void createRequest(
            @Param("requestedUserId") Long requestedUserId,
            @Param("receivedUserId") Long receivedUserId)
            throws SQLException;


    @Modifying
    @Query("UPDATE requests SET is_approved = true " +
            "WHERE request_user_id = :requestedUserId AND received_user_id = :receivedUserId")
    void approveRequest(
            @Param("requestedUserId") Long requestedUserId,
            @Param("receivedUserId") Long receivedUserId);

    @Modifying
    @Query("DELETE FROM requests r " +
            "USING users u " +
            "WHERE r.request_user_id = :requestedUserId" +
            " and r.received_user_id = :receivedUserId" +
            " and is_approved=false")
    void deleteRequest(
            @Param("requestedUserId") Long requestedUserId,
            @Param("receivedUserId") Long receivedUserId);

    @Query("SELECT * FROM requests r WHERE request_user_id = :userId")
    List<UserRequest> getUserRequests(
            @Param("userId") Long userId);

    @Query("SELECT * FROM requests r WHERE r.request_id = :requestId AND r.request_user_id = :userId")
    UserRequest getUserRequest(
            @Param("userId") Long userId,
            @Param("requestId") Long requestId);

    @Modifying
    @Query("UPDATE requests" +
            " SET is_approved = true" +
            " WHERE request_id = :requestId")
    void approveRequest(
            @Param("requestId") Long requestId);

    @Modifying
    @Query("UPDATE requests" +
            " SET is_approved = false" +
            " WHERE request_id = :requestId")
    void declineRequest(
            @Param("requestId") Long requestId);

    @Query("DELETE FROM requests r " +
            "WHERE r.request_id = :requestId")
    void deleteRequest(
            @Param("requestId") Long requestId);

    @Query("SELECT * FROM requests r" +
            " WHERE r.request_user_id = :requestedUserId" +
            " AND r.received_user_id = :receivedUserId")
    UserRequest getUserRequestByUsersIds(
            @Param("requestedUserId") Long requestedUserId,
            @Param("receivedUserId") Long receivedUserId);
}
