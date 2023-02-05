package org.example.apigateway.controller;

import lombok.RequiredArgsConstructor;
import org.example.apigateway.dto.MessageDto;
import org.example.apigateway.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{messageId}")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable Long messageId) {

        MessageDto messageBody = messageService.getMessageById(messageId);
        if (!messageBody.getMessageBody().isEmpty()) {
            return ResponseEntity.ok().body(messageBody);
        }
        return ResponseEntity.badRequest().build();
    }

}
