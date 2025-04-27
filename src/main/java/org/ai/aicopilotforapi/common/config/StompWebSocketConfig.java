package org.ai.aicopilotforapi.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker  // ⟵ Enables STOMP broker and SimpMessagingTemplate bean
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enable a simple in-memory message broker for destinations prefixed with /topic and /queue
        registry.enableSimpleBroker("/topic", "/queue");  // ⟵ required for SimpMessagingTemplate :contentReference[oaicite:3]{index=3}
        // Prefix for messages bound for @MessageMapping methods
        registry.setApplicationDestinationPrefixes("/app");  // ⟵ routes client messages to @MessageMapping handlers :contentReference[oaicite:4]{index=4}
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register STOMP endpoint at /stomp with SockJS fallback
        registry.addEndpoint("/stomp")
                .setAllowedOrigins("*")
                .withSockJS();  // ⟵ fallback for browsers that don't support WebSocket :contentReference[oaicite:5]{index=5}
    }
}
