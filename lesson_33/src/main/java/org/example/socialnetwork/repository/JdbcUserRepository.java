package org.example.socialnetwork.repository;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.exceptions.UserNotFoundException;
import org.example.socialnetwork.model.User;
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
public class JdbcUserRepository implements UserRepository {
    private final Connection connection;
    private static final String GET_ALL_USERS =
            "SELECT user_id,name,role,password,created_at " +
            "FROM users";
    private static final String SAVE_USERNAME_AND_PASSWORD =
            "INSERT INTO users(name,role,password) " +
            "VALUES(?,?,?)";
    private static final String GET_USER_FROM_USERS =
            "SELECT user_id " +
            "FROM users " +
            "WHERE name = ? and password = ?";

    private static final String GET_USER_FROM_USERS_BY_NAME_ROLE =
            "SELECT user_id,name,role,created_at " +
                    "FROM users " +
                    "WHERE name = ? and role = ?";
    private static final String GET_NAME_PASSWORD_FROM_USERS =
            "SELECT name,password " +
            "FROM users " +
            "WHERE name = ? and password = ?";
    private static final String GET_USERNAME_FROM_USERS =
            "SELECT name " +
            "FROM users " +
            "WHERE name = ?";

    private User createUser(Long userId, String name, String role, String password, Date createdAt) {
        return new User(userId, name, role, createdAt);
    }

    private User createUser(Long userId, String name, String role, Date createdAt) {
        return new User(userId, name, role, createdAt);
    }

    @Override
    public List<User> findUsers() {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL_USERS);
            final List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(createUser(
                        rs.getLong("user_id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("password"),
                        rs.getDate("created_at"))
                );
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void createUser(String name, String role, String password) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_USERNAME_AND_PASSWORD,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, role);
            statement.setString(3, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(String name, String role) {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_FROM_USERS_BY_NAME_ROLE,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, role);
            User user = null;
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user= createUser(
                        rs.getLong("user_id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getDate("created_at"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getUserId(String name, String password) {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_FROM_USERS)) {
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getLong("user_id");
            }
            throw new UserNotFoundException("User with name:" + name + " is not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean validateUser(String name, String password) {
        try (PreparedStatement statement = connection.prepareStatement(GET_NAME_PASSWORD_FROM_USERS,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet queryResult = statement.executeQuery();
            return queryResult.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean validateUsername(String username) {
        try (PreparedStatement statement = connection.prepareStatement(GET_USERNAME_FROM_USERS,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, username);
            ResultSet queryResult = statement.executeQuery();
            return queryResult.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
