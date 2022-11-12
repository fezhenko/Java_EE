package org.example.socialnetwork.repository;

import org.example.socialnetwork.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserRequestRepository implements UserRequestRepository {
    private final Connection connection;

    public JdbcUserRequestRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findUsersApprovedRequest(Long userId) {
        final String GET_USERS_WHO_APPROVED_REQUESTS =
                "SELECT DISTINCT u.user_id,u.name,u.role,u.created_at " +
                        "FROM requests r " +
                        "JOIN users u ON r.received_user_id=u.user_id " +
                        "WHERE r.request_user_id = ? AND r.isapproved = true";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_WHO_APPROVED_REQUESTS)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            final List<User> usersApprovedRequest = new ArrayList<>();
            while (resultSet.next()) {
                final User approvedUser = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("role"),
                        resultSet.getDate("created_at"));
                usersApprovedRequest.add(approvedUser);
            }
            return usersApprovedRequest;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findUsersWithNotApprovedRequest(Long userId) {
        final String GET_USERS_WHO_SENT_REQUESTS_TO_USER =
                "SELECT DISTINCT u.user_id,u.name,u.role,u.created_at " +
                "FROM requests r " +
                "JOIN users u ON r.request_user_id=u.user_id " +
                "WHERE r.received_user_id = ? AND r.isapproved = false";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_WHO_SENT_REQUESTS_TO_USER,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            final List<User> usersWithIncomingRequests = new ArrayList<>();
            while (resultSet.next()) {
                final User user = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("role"),
                        resultSet.getDate("created_at"));
                usersWithIncomingRequests.add(user);
            }
            return usersWithIncomingRequests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findNotApprovedRequestsByUser(Long userId) {
        final String GET_ALL_OUTCOMING_REQUESTS =
                "SELECT u.user_id,u.name,u.role,created_at " +
                "FROM requests r " +
                "JOIN users u ON r.request_user_id =u.user_id " +
                "WHERE r.received_user_id = ? AND isApproved = false";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_OUTCOMING_REQUESTS)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            final List<User> requestedFriendUsers = new ArrayList<>();
            while (resultSet.next()) {
                final User requestedUser = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("role"),
                        resultSet.getDate("created_at"));
                requestedFriendUsers.add(requestedUser);
            }
            return requestedFriendUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createRequest(Long requestedUserId, Long receivedUserId) {
        final String CREATE_REQUEST =
                "INSERT INTO requests(request_user_id,received_user_id,isApproved)" +
                "SELECT ?,?,false;";
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
        final String GET_USER_REQUEST =
                "SELECT isapproved " +
                "FROM requests r " +
                "JOIN users requested ON r.request_user_id = requested.user_id " +
                "JOIN users received ON r.received_user_id = received.user_id " +
                "WHERE requested.name = ? AND received.name=?";
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
        final String APPROVE_USER_REQUEST =
                "UPDATE requests SET isApproved = true " +
                "WHERE request_user_id = ? AND received_user_id = ?;";
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
        final String DELETE_REQUEST =
                "DELETE FROM requests r " +
                "USING users u " +
                "WHERE r.request_user_id = ? and r.received_user_id = ? and isapproved=false;";
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
