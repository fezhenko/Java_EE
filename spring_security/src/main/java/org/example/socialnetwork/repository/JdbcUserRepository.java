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
    List<AppUser> users;

    private static final String GET_ALL_USERS =
            "SELECT user_id,name,role,password,created_at " +
            "FROM users";
    private static final String SAVE_USER =
            "INSERT INTO users(name,password,role) " +
            "VALUES(?,?,?)";


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
        return findUsers().stream()
                .anyMatch(user -> user.getName().equals(name)
                        && passwordEncoder.matches(password, user.getPassword()));
    }
    @Override
    public boolean validateUsername(String username) {
        return findUsers().stream()
                .anyMatch(user -> user.getName().equals(username));
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
                        && passwordEncoder.matches(password, user.getPassword()))
                .map(AppUser::getUserId)
                .findFirst()
                .orElse(null);
    }
    @Override
    public AppUser getUser(String name, String password, String role) {
        return findUsers().stream()
                .filter(user -> user.getName().equals(name)
                        && passwordEncoder.matches(password, user.getPassword())
                        && user.getRole().equals(role))
                .findFirst()
                .orElse(null);
    }

    @Override
    public AppUser getUser(String username, String password) {
        return findUsers().stream()
                .filter(user -> user.getName().equals(username)
                    && passwordEncoder.matches(password, user.getPassword()))
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
