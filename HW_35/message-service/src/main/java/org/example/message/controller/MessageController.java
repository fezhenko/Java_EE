package org.example.message.controller;

import lombok.RequiredArgsConstructor;
import org.example.message.converter.MessageConverter;
import org.example.message.dto.MessageDto;
import org.example.message.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/messages")
@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    private final MessageConverter messageConverter;

    @GetMapping("/{messageId}")
    public ResponseEntity<MessageDto> getMessage(@PathVariable Long messageId) {
        String message = messageService.getMessage(messageId);
        if (!message.isEmpty()) {
            return ResponseEntity.ok(messageConverter.toDto(message));
        }
        return ResponseEntity.badRequest().build();
    }
}
