package org.ai.aicopilotforapi.demo.controller;

import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class StompChatController {
    private final SimpMessagingTemplate template;
    public StompChatController(SimpMessagingTemplate tmpl) {
        this.template = tmpl;
    }

    @MessageMapping("/chat.send")
    public void send(@Payload String msg) {
        template.convertAndSend("/topic/messages", msg);
    }
}
