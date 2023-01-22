package org.example.socialnetwork.repository;

import org.example.socialnetwork.model.AppUser;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendsRepository extends Repository<AppUser, Long> {
    @Query("select u2.user_id, u2.name, u2.role, u2.created_at" +
            " from" +
            " (select f.friend_user_id" +
            " from friends f" +
            " join requests r on f.request_id = r.request_id" +
            " join users u on r.request_user_id = u.user_id" +
            " where r.is_approved = true and u.user_id = :userId) get_friend_id" +
            " join users u2 on friend_user_id = u2.user_id;")
    List<AppUser> findFriends(@Param("userId") Long userId);

    @Modifying
    @Query("INSERT INTO friends (friend_user_id, request_id) VALUES (:friendId, :requestid)")
    void addFriend(@Param("friendId") Long friendId,
                   @Param("requestId") Long requestId);


    @Query("""
            (select u2.user_id, u2.name, u2.role, u2.created_at from
                (select f.friend_user_id from friends f
                    join requests r on f.request_id = r.request_id
                    join users u on r.request_user_id = u.user_id
                                         where r.is_approved = true and u.user_id = :userId) get_friend_id
                    join users u2 on u2.user_id = :friendId)""")
    AppUser getFriend(Long userId, Long friendId);

    @Modifying
    @Query("DELETE FROM friends f USING users u WHERE u.user_id = :userId AND f.friend_user_id = :friendId")
    void removeFriend(Long userId, Long friendId);

}
