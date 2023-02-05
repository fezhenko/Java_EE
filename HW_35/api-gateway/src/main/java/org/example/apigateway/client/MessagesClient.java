package org.example.apigateway.client;

import org.example.apigateway.dto.MessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "message-service", url = "${services.message-service.url}/api/v1/messages")
public interface MessagesClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{messageId}")
    MessageDto getMessageById(@PathVariable Long messageId);

}
