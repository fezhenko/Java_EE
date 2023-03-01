package org.example.message.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("messages")
@Value
@Builder
public class Message {
    @Id
    @Column("message_id")
    Long messageId;
    @Column("message")
    String messageBody;
    @Column("created_at")
    Date createdAt;
}
