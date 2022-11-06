package org.example.login.repository;

import org.example.login.model.User;
import org.example.login.model.UserRequest;

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
    public List<UserRequest> findApprovedUserRequests() {
        final String GET_ALL_INCOMING_REQUESTS =
                "SELECT request_id,request_user_id,received_user_id,isApproved,created_at " +
                "FROM requests WHERE isApproved = true;";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL_INCOMING_REQUESTS);
            final List<UserRequest> incomingUserRequests = new ArrayList<>();
            while (rs.next()) {
                final UserRequest userRequest = new UserRequest(
                        rs.getLong("request_id"),
                        rs.getLong("request_user_id"),
                        rs.getLong("received_user_id"),
                        rs.getBoolean("isApproved"),
                        rs.getDate("created_at"));
                incomingUserRequests.add(userRequest);
            }
            return incomingUserRequests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findUsersWithNotApprovedRequest(String username) {
        final String GET_USERS_WITH_INCOMING_REQUESTS =
                "SELECT DISTINCT requested_user.user_id,requested_user.name,requested_user.role,requested_user.created_at " +
                "FROM requests r " +
                "JOIN users requested_user ON r.request_user_id=requested_user.user_id " +
                "JOIN users received_user ON r.received_user_id=received_user.user_id " +
                "WHERE received_user.name = ? AND r.isapproved = false";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_WITH_INCOMING_REQUESTS,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, username);
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
    public List<UserRequest> findNotApprovedUserRequests() {
        final String GET_ALL_OUTCOMING_REQUESTS =
                "SELECT request_id,request_user_id,received_user_id,isApproved,created_at " +
                "FROM requests " +
                "WHERE isApproved = false";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL_OUTCOMING_REQUESTS);
            final List<UserRequest> outcomingUserRequests = new ArrayList<>();
            while (rs.next()) {
                final UserRequest userRequest = new UserRequest(
                        rs.getLong("request_id"),
                        rs.getLong("request_user_id"),
                        rs.getLong("received_user_id"),
                        rs.getBoolean("isApproved"),
                        rs.getDate("created_at"));
                outcomingUserRequests.add(userRequest);
            }
            return outcomingUserRequests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveRequest(String requestedUsername, String receivedUsername) {
        final String SAVE_USER_REQUEST =
                "INSERT INTO requests(request_user_id,received_user_id,isApproved)" +
                "VALUES(?,?,false)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER_REQUEST,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, requestedUsername);
            preparedStatement.setString(2, receivedUsername);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isRequestApproved(String requestedUsername, String receivedUsername) {
        final String GET_USER_REQUEST =
                "SELECT isapproved " +
                "FROM requests r " +
                "JOIN users requested ON r.request_user_id = requested.user_id " +
                "JOIN users received ON r.received_user_id = received.user_id " +
                "WHERE requested.name = ? AND received.name=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_REQUEST,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, requestedUsername);
            preparedStatement.setString(2, receivedUsername);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void approveRequest(String requestedUsername, String receivedUsername) {
        final String APPROVE_USER_REQUEST =
                "INSERT INTO requests(request_user_id,received_user_id,isApproved)" +
                "VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(APPROVE_USER_REQUEST,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, requestedUsername);
            preparedStatement.setString(2, receivedUsername);
            preparedStatement.setBoolean(3, true);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRequest(Long requestId) {
        final String DELETE_REQUEST = "DELETE FROM requests WHERE request_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, requestId);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
