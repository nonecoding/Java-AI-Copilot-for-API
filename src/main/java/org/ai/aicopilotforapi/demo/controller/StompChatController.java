package org.ai.aicopilotforapi.demo.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class StompChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public StompChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestParam String msg) {
        // Broadcast to all subscribers of /topic/messages
        messagingTemplate.convertAndSend("/topic/messages", msg);
    }
}
