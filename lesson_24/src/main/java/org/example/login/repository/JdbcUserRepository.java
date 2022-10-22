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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(User user) {

        String GET_USER_FROM_USERS_QUERY = "SELECT * FROM users WHERE name = ?";
        User userFromDatabase = null;
        
        try {
            PreparedStatement statement = connection.prepareStatement(GET_USER_FROM_USERS_QUERY);
            statement.setString(1, user.getName());
            ResultSet rs = statement.executeQuery(GET_USER_FROM_USERS_QUERY);
            
            if (rs.next()) {
                userFromDatabase = new User(rs.getString("name"), rs.getString("password"));
            }
            else{
                throw new SQLException("User does not exist");
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return userFromDatabase;
    }

    @Override
    public boolean validateUser(User user) {

        String GET_NAME_PASSWORD_FROM_USERS_QUERY = "SELECT name,password FROM users WHERE name = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(GET_NAME_PASSWORD_FROM_USERS_QUERY);
            statement.setString(1, user.getName());
            ResultSet queryResult = statement.executeQuery();

            Map<String,String> existedUserData = new HashMap<>();
            if (queryResult.next()){
                existedUserData.put(queryResult.getString("name"),
                        queryResult.getString("password"));

                Map<String,String> actualUserData = new HashMap<>();
                actualUserData.put(user.getName(),user.getPassword());

                if(existedUserData.equals(actualUserData)){
                    return true;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
