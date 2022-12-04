package org.example.socialnetwork.repository;

import org.example.socialnetwork.model.AppUser;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class JdbcUserRepository implements UserRepository {
    private final Connection connection;
    private final PasswordEncoder passwordEncoder;
    private final List<AppUser> users;

    private static final String GET_ALL_USERS =
            "SELECT user_id,name,role,password,created_at " +
            "FROM users";
    private static final String SAVE_USER =
            "INSERT INTO users(name,password,role) " +
            "VALUES(?,?,?)";
    private static final String GET_USER_FROM_USERS =
            "SELECT user_id " +
            "FROM users " +
            "WHERE name = ? and password = ?";

    private static final String GET_USER_FROM_USERS_BY_NAME_ROLE_PASSWORD =
            "SELECT user_id,name,role,created_at " +
                    "FROM users " +
                    "WHERE name = ? and role = ? and password = ?";
    private static final String GET_NAME_PASSWORD_FROM_USERS =
            "SELECT name,password " +
            "FROM users " +
            "WHERE name = ? and password = ?";

    private static final String GET_USER_BY_ID =
            "SELECT user_id,name,role,created_at " +
                    "FROM users " +
                    "WHERE user_id = ?";
    private static final String GET_USERNAME_FROM_USERS =
            "SELECT name " +
            "FROM users " +
            "WHERE name = ?";

    public JdbcUserRepository(final Connection connection, final PasswordEncoder passwordEncoder) {
        this.connection = connection;
        this.passwordEncoder = passwordEncoder;
        this.users = findUsers();
    }

    private AppUser createUser(Long userId, String name, String password, String role, Date createdAt) {
        return new AppUser(userId, name, password, role, createdAt);
    }

    @Override
    public List<AppUser> findUsers() {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL_USERS);
            final List<AppUser> appUsers = new ArrayList<>();
            while (rs.next()) {
                appUsers.add(createUser(
                        rs.getLong("user_id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getDate("created_at"))
                );
            }
            return appUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void createUser(String name, String password, String role) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_USER,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, password);
            statement.setString(3, role);
            statement.executeUpdate();
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

    @Override
    public AppUser getUser(String username) {
        return findUsers().stream()
                .filter(user -> user.getName().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Long getUserId(String name, String password) {
        return findUsers().stream()
                .filter(user -> user.getName().equals(name)
                        && user.getPassword().equals(password))
                .map(AppUser::getUserId)
                .findFirst()
                .orElse(null);
    }
    @Override
    public AppUser getUser(String name, String password, String role) {
        return findUsers().stream()
                .filter(user -> user.getName().equals(name)
                        && user.getPassword().equals(password)
                        && user.getRole().equals(role))
                .findFirst()
                .orElse(null);
    }

    @Override
    public AppUser getUser(String username, String password) {
        return findUsers().stream()
                .filter(user -> user.getName().equals(username)
                    && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    public AppUser getUserById(Long userId) {
        return findUsers().stream()
                .filter(user -> user.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }
}
