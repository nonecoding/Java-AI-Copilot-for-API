package org.ai.aicopilotforapi.common.config;

import org.ai.aicopilotforapi.common.handler.ChatWebSocketHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Value("${app.ws.endpoint:/ws/chat}")
    private String wsEndpoint;

    @Value("${spring.websocket.allowed-origins:*}")
    private String[] allowedOrigins;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler(), wsEndpoint)
                .setAllowedOrigins(allowedOrigins)
                .withSockJS();  // remove .withSockJS() if you don’t need fallback
    }

    @Bean
    public ChatWebSocketHandler chatHandler() {
        return new ChatWebSocketHandler();
    }
}
