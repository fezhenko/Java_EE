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
    public List<UserRequest> findUserRequests() {
        final String GET_ALL_USERS_REQUESTS = "select id,user_id,request_status,created_at from requests";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL_USERS_REQUESTS);
            final List<UserRequest> userRequests = new ArrayList<>();
            while (rs.next()) {
                final UserRequest userRequest = new UserRequest(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("request_status"),
                        rs.getDate("created_at"));
                userRequests.add(userRequest);
            }
            return userRequests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveRequest(User user, String userRequestStatus) {
        final String SAVE_USER_REQUEST = "INSERT INTO requests(user_id,request_status) VALUES(?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER_REQUEST,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, userRequestStatus);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isRequestSend(String username) {
        final String GET_USER_REQUEST = "SELECT id FROM requests r join users u ON r.user_id = u.id WHERE u.name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_REQUEST)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getRequestStatus(String username) {
        final String GET_USER_REQUEST_STATUS = "SELECT request_status FROM requests r join users u" +
                " ON r.user_id = u.id WHERE u.name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_REQUEST_STATUS)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString("request_status");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
