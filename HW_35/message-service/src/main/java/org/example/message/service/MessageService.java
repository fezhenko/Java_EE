package org.example.message.service;

import lombok.RequiredArgsConstructor;
import org.example.message.repository.MessagesRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessagesRepository messagesRepository;

    public String getMessage(Long messageId) {
        return messagesRepository.getMessageByMessageId(messageId);
    }
}
