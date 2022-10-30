package org.example.login.repository;

import org.example.login.model.User;

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
        final String GET_ALL_USERS = "select id,name,role,password,created_at from users";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_USERS);
            final List<User> users = new ArrayList<>();
            while (rs.next()) {
                final User user = new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("password"),
                        rs.getDate("created_at"));
                users.add(user);
            }
            statement.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(User user) {

        final String SAVE_USERNAME_AND_PASSWORD = "INSERT INTO users(name,role,password) VALUES(?,?,?);";

        try {
            PreparedStatement statement = connection.prepareStatement(SAVE_USERNAME_AND_PASSWORD,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getRole());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(User user) {

        final String GET_USER_FROM_USERS_QUERY = "SELECT id,name,role,password,created_at FROM users WHERE name = ?";
        User userFromDatabase;
        try {
            PreparedStatement statement = connection.prepareStatement(GET_USER_FROM_USERS_QUERY);
            statement.setString(1, user.getName());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                userFromDatabase = new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("password"),
                        rs.getDate("created_at"));
            } else {
                throw new IllegalArgumentException();
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userFromDatabase;
    }

    @Override
    public boolean validateUser(String name, String password) {

        User user = new User(name, password);
        final String GET_NAME_PASSWORD_FROM_USERS_QUERY = "SELECT name,password FROM users WHERE name = ?" +
                " and password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(GET_NAME_PASSWORD_FROM_USERS_QUERY);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());

            ResultSet queryResult = statement.executeQuery();
            if (queryResult.next()) {
                statement.close();
                return true;
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
