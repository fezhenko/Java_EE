package org.example.login.repository;

import org.example.login.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUserRepository implements UserRepository {
    private final Connection connection;

    public JdbcUserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findUsers() {
        String GET_ALL_USERS = "select * from users";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_USERS);
            final List<User> users = new ArrayList<>();
            while (rs.next()) {
                final User user = new User(rs.getString("name"),rs.getString("password"));
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

        String SAVE_USERNAME_AND_PASSWORD = "INSERT INTO users(name, password) VALUES(?,?);";

        try {
            PreparedStatement statement = connection.prepareStatement(SAVE_USERNAME_AND_PASSWORD,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(User user) {

        String GET_USER_FROM_USERS_QUERY = "SELECT name,password FROM users WHERE name = ?";
        User userFromDatabase = null;
        
        try {
            PreparedStatement statement = connection.prepareStatement(GET_USER_FROM_USERS_QUERY);
            statement.setString(1, user.getName());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                userFromDatabase = new User(rs.getString("name"), rs.getString("password"));
            }
            else {
                throw new IllegalArgumentException();
            }
            statement.close();
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userFromDatabase;
    }

    @Override
    public boolean validateUser(String name, String password) {

        User user = new User(name,password);
        String GET_NAME_PASSWORD_FROM_USERS_QUERY = "SELECT name,password FROM users WHERE name = ? and password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(GET_NAME_PASSWORD_FROM_USERS_QUERY);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());

            ResultSet queryResult = statement.executeQuery();
            if (queryResult.next()) {
                return true;
            }
            statement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
