package org.example.apigateway.service;

import lombok.RequiredArgsConstructor;
import org.example.apigateway.client.MessagesClient;
import org.example.apigateway.dto.MessageDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessagesClient messagesClient;

    public MessageDto getMessageById(Long messageId) {
        return messagesClient.getMessageById(messageId);
    }
}
