package org.example.message.dto;

import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
public class MessageDto {
    String messageBody;
}
