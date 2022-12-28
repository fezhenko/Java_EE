package org.example.socialnetwork.repository;

import org.example.socialnetwork.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserRepository implements UserRepository {
    private final Connection connection;

    public JdbcUserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findUsers() {
        final String GET_ALL_USERS = "select user_id,name,role,password,created_at from users";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL_USERS);
            final List<User> users = new ArrayList<>();
            while (rs.next()) {
                final User user = new User(
                        rs.getLong("user_id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("password"),
                        rs.getDate("created_at"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(User user) {
        final String SAVE_USERNAME_AND_PASSWORD =
                "INSERT INTO users(name,role,password) " +
                "VALUES(?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(SAVE_USERNAME_AND_PASSWORD,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getRole());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(String username, String password) {
        final String GET_USER_FROM_USERS =
                "SELECT user_id,name,role,password,created_at " +
                "FROM users " +
                "WHERE name = ? and password = ?";
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_FROM_USERS)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong("user_id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("password"),
                        rs.getDate("created_at"));
            } else {
                throw new IllegalArgumentException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validateUser(String name, String password) {
        final String GET_NAME_PASSWORD_FROM_USERS =
                "SELECT name,password " +
                "FROM users " +
                "WHERE name = ? and password = ?";
        try (PreparedStatement statement = connection.prepareStatement(GET_NAME_PASSWORD_FROM_USERS,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet queryResult = statement.executeQuery();
            if (queryResult.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
