package org.example.message.converter;

import org.example.message.dto.MessageDto;
import org.mapstruct.Mapper;

@Mapper
public interface MessageConverter {
    MessageDto toDto(String messageBody);
}
