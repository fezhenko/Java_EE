package org.example.message.repository;

import org.example.message.model.Message;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface MessagesRepository extends Repository<Message, Long> {
    @Query("SELECT m.message FROM messages m WHERE m.message_id = :messageId")
    String getMessageByMessageId(@Param("messageId") Long messageId);
}
