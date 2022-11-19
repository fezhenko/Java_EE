package org.example.springmvc.repository;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class JdbcFriendRequest implements FriendRequest {
    private final Connection connection;
    private static final String GET_USERS_WHO_APPROVED_REQUESTS =
            "SELECT DISTINCT u.user_id, u.name, u.role, u.created_at " +
            "FROM requests r " +
            "JOIN users u ON r.received_user_id=u.user_id " +
            "WHERE r.request_user_id = ? AND r.is_approved = true";
    private static final String GET_USERS_FOR_WHOM_FRIEND_REQUESTS_IS_SENT =
            "SELECT u.user_id, u.name, u.role, u.created_at " +
            "FROM requests r " +
            "JOIN users u ON r.request_user_id = u.user_id " +
            "WHERE r.received_user_id = ? AND is_approved = false";
    private static final String CREATE_REQUEST =
            "INSERT INTO requests(request_user_id,received_user_id,is_approved)" +
            "SELECT ?,?,false;";
    private static final String GET_USER_REQUEST =
            "SELECT is_approved " +
            "FROM requests r " +
            "JOIN users requested ON r.request_user_id = requested.user_id " +
            "JOIN users received ON r.received_user_id = received.user_id " +
            "WHERE requested.user_id = ? AND received.user_id = ?";
    private static final String APPROVE_USER_REQUEST =
            "UPDATE requests SET is_approved = true " +
            "WHERE request_user_id = ? AND received_user_id = ?;";
    private static final String DELETE_REQUEST =
            "DELETE FROM requests r " +
            "USING users u " +
            "WHERE r.request_user_id = ? and r.received_user_id = ? and is_approved=false;";

    private User createUser(Long userId, String name, String role, Date createdAt) {
        return new User(userId, name, role, createdAt);
    }
    private List<User> getUsers(Long userId, String getUsersRequests) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getUsersRequests,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            final List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(createUser(
                        resultSet.getLong("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("role"),
                        resultSet.getDate("created_at"))
                );
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<User> findUsersApprovedRequest(Long userId) {
        return getUsers(userId, GET_USERS_WHO_APPROVED_REQUESTS);
    }
    @Override
    public List<User> findNotApprovedRequestsByUser(Long userId) {
        return getUsers(userId, GET_USERS_FOR_WHOM_FRIEND_REQUESTS_IS_SENT);
    }
    @Override
    public void createRequest(Long requestedUserId, Long receivedUserId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_REQUEST,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, requestedUserId);
            preparedStatement.setLong(2, receivedUserId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean isRequestApproved(Long requestedUserId, Long receivedUserId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_REQUEST,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, requestedUserId);
            preparedStatement.setLong(2, receivedUserId);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void approveRequest(Long requestedUserId, Long receivedUserId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(APPROVE_USER_REQUEST,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, requestedUserId);
            preparedStatement.setLong(2, receivedUserId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteRequest(Long requestedUserId, Long receivedUserId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, requestedUserId);
            preparedStatement.setLong(2, receivedUserId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
