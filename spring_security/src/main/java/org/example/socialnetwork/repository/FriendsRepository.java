package org.example.socialnetwork.repository;

import org.example.socialnetwork.model.AppUser;
import org.example.socialnetwork.model.Friend;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendsRepository extends Repository<Friend, Long> {
    @Query("select id, friend_user_id, request_id, created_at from friends")
    List<AppUser> findFriends();

    @Modifying
    @Query("INSERT INTO friends (friend_user_id, request_id) VALUES (:friendId, :requestid)")
    public void addFriend(@Param("friendId") Long friendId,
                          @Param("requestId") Long requestId);

    @Modifying
    @Query("DELETE FROM friends f USING users u WHERE f.friend_user_id = :friendId")
    public void removeFriend(@Param("friendId") Long friendId);

    @Query("select u.user_id, u.name, u.role, u.created_at from users u" +
            " join friends f on u.user_id=:friendId")
    AppUser getFriend(Long friendId);
}
