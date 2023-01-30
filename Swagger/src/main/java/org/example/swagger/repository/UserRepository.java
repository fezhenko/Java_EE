package org.example.swagger.repository;

import org.example.swagger.model.AppUser;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends Repository<AppUser, Long> {
    @Query("SELECT user_id, name, role, password, created_at FROM users")
    List<AppUser> findUsers();

    @Modifying
    @Query("INSERT INTO users(user_id, name, password, role, created_at) " +
            "VALUES(default,?,?,?,now()) ")
    AppUser createUser(String name, String password, String role);

    @Query("SELECT user_id, name, password, role, created_at " +
            "FROM users u " +
            "WHERE name = :username ")
    AppUser getUser(@Param("username") String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM users u WHERE u.name = :username")
    boolean validateUser(@Param("username") String username);

    @Query("SELECT user_id, name, role, password, created_at " +
            "FROM users " +
            "WHERE user_id = :userId")
    AppUser getUserById(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE users SET name = :username, role = :role " +
            "WHERE user_id = :userId")
    AppUser updateUser(
            @Param("userId") Long userId,
            @Param("username") String username,
            @Param("role") String role);

    @Modifying
    @Query("DELETE FROM users u WHERE u.user_id = :userId")
    void deleteAppUserByUserId(@Param("userId") Long userId);

}
